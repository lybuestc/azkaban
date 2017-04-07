package mytest;

/**
 * Created by lybuestc on 17/4/6.
 */
public class MyFlowRunnerManager implements MyEventListener ,Runnable{

    MyFlow myFlow;
    MyFlowWatcher watcher = null;
    public MyFlowRunnerManager(MyFlow myFlow) {
        this.myFlow = myFlow;
    }

    @Override
    public void handleEvent(MyEvent event) {
        if(event.getType()== MyEvent.Type.FLOW_FINISHED){
            System.out.println("flow已完成");
        }
    }

    public void execute(){
        MyExecuteFlowBase flow = new MyExecuteFlowBase(myFlow);
        MyFlowRunner runner =
                new MyFlowRunner(flow);
        watcher = new MyLocalFlowWatcher(runner);
        runner.run();
    }

    @Override
    public void run() {
        execute();
    }
}
