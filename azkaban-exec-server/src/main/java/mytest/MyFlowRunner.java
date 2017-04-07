package mytest;

import azkaban.executor.Status;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lybuestc on 17/4/6.
 */
public class MyFlowRunner  extends MyEventHandler implements Runnable{

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    private boolean flowFinished = false;
    private ConcurrentLinkedQueue<MyExecutableNode> finishedNodes = new ConcurrentLinkedQueue<>();
    private Object mainSyncObj = new Object();
    private MyJobRunnerEventListener listener = new MyJobRunnerEventListener();
    private  MyExecuteFlowBase flow;

    public MyFlowRunner(MyExecuteFlowBase flow) {
        this.flow = flow;
    }

    @Override
    public void run() {
        runFlow();
        this.fireEventListeners(MyEvent.create(this, MyEvent.Type.FLOW_FINISHED));
    }

    private class MyJobRunnerEventListener implements MyEventListener{
        @Override
        public void handleEvent(MyEvent event) {

            if(event.getType() == MyEvent.Type.JOB_FINISHED){
                MyJobRunner runner = (MyJobRunner) event.getRunner();
                //唤醒使用flowManager来决定下一步如何操作
                MyExecutableNode node = runner.getNode();
                finishedNodes.add(node);
            }
        }
    }

    public void setListener(MyJobRunnerEventListener listener) {
        this.listener = listener;
    }

    private void runFlow(){
        runReadyJob(this.flow);
        while (!flowFinished) {
            synchronized (mainSyncObj) {
                progressGraph();
            }
        }
    }

    private boolean progressGraph() {
        //扫描已完成的node列表;其出度的节点为下一个备选节点
        HashSet<MyExecutableNode> nodesToCheck = new HashSet<MyExecutableNode>();
        for (MyExecutableNode node : finishedNodes) {
            Set<String> outNodeIds = node.getOutNodes();//出度节点,即下一个刻需要触发的job
            MyExecuteFlowBase parentFlow = node.getParentFlow();

            if (outNodeIds.isEmpty()) {
                //没有out节点证明到最后一个节点了,flow处理完成,将
                // There's no outnodes means it's the end of a flow, so we finalize
                // and fire an event.
                this.fireEventListeners(MyEvent.create(this, MyEvent.Type.FLOW_FINISHED));
                flowFinished = true;
                System.out.println("flow has been finished");
            }
            for(String outNodes:outNodeIds){
                nodesToCheck.add(flow.getExecutableNodes().get(outNodes));
            }
        }

        // Runs candidate jobs. The code will check to see if they are ready to run
        // before
        // Instant kill or skip if necessary.
        boolean jobsRun = false;
        for (MyExecutableNode node : nodesToCheck) {
            if (MyStatus.isStatusFinished(node.getStatus())
                    || MyStatus.isStatusRunning(node.getStatus())) {
                // Really shouldn't get in here.
                continue;
            }
            runReadyJob(node);
        }
        return true;
    }

    private void runReadyJob(MyExecutableNode node){
        if (node.getStatus() == Status.READY) {
            if (node instanceof MyExecuteFlowBase) {
                //因为单个节点ExecutableNode类型也可以用ExecutableFlowBase类型来个内部流,
                //因此第一遍传进来的是真个flow图的类型,然后通过了这一步之后再下一次调用runReadyJob时才是ExecutableNode
                //类型,进行真正的执行
                //每个flow只会调用一遍
                MyExecuteFlowBase flow = ((MyExecuteFlowBase) node);
                flow.setStatus(Status.RUNNING);


                for (String startNodeId : ((MyExecuteFlowBase) node).getStartNode()) {
                    MyExecutableNode startNode = flow.getExecutableNode(startNodeId);
                    // 将没有入度的节点开始执行
                    runReadyJob(startNode);
                }
            } else {
                // 上面筛选出来的开始执行的节点
                runExecutableNode(node);
            }
        }
    }

    private void runExecutableNode(MyExecutableNode node){
        node.setStatus(Status.QUEUED);
        MyJobRunner runner = new MyJobRunner();
        runner.setNode(node);
        runner.addListener(listener);
        executorService.submit(runner);
    }
}
