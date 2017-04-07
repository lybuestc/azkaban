package mytest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by lybuestc on 17/4/6.
 */
public class MyFlowMain {

    //static String dependency="{\"dependencys\": [{ \"jobName\":\"jobA\" , \"dependency\":\"start\" },{ \"jobName\":\"jobB\" , \"dependency\":\"start\" },{ \"jobName\":\"jobC\" ,\"dependency\":\"jobA,jobB\" },{ \"jobName\":\"jobD\" ,\"dependency\":\"jobC\" }]}";


    public static void main(String[] args){

        MyNode start = new MyNode("start","start");
        MyNode jobA = new MyNode("jobA","jobA");
        MyNode jobB = new MyNode("jobB","jobB");
        MyNode jobC = new MyNode("jobC","jobC");
        MyNode jobD = new MyNode("jobD","jobD");

        MyFlow myFlow = new MyFlow();
        HashMap<String, MyNode> nodes =  myFlow.getNodes();
        HashMap<String, MyEdge> edges = myFlow.getEdges();
        HashMap<String, HashSet<MyEdge>> inEdges = myFlow.getInEdges();
        HashMap<String, HashSet<MyEdge>> outEdges = myFlow.getOutEdges();
        nodes.put("start",start);
        nodes.put("jobA",jobA);
        nodes.put("jobB",jobB);
        nodes.put("jobC",jobC);
        nodes.put("jobD",jobD);

        edges.put("start>>jobA",new MyEdge(start,jobA));
        edges.put("start>>jobB",new MyEdge(start,jobB));
        edges.put("jobA>>jobC",new MyEdge(jobA,jobC));
        edges.put("jobB>>jobC",new MyEdge(jobB,jobC));
        edges.put("jobC>>jobD",new MyEdge(jobC,jobD));



        for(String node:nodes.keySet()){
            inEdges.put(node,new HashSet<MyEdge>());
            outEdges.put(node,new HashSet<MyEdge>());
        }
        for(MyEdge edge:edges.values()){
            String sourceNodeName = edge.getSource().getId();
            String targetNodeName = edge.getTarget().getId();
            outEdges.get(sourceNodeName).add(edge);//对source点来说,一条边是出边
            inEdges.get(targetNodeName).add(edge);//对target点来说,一条边是入边
        }

        myFlow.setNodes(nodes);
        myFlow.setEdges(edges);
        myFlow.setInEdges(inEdges);
        myFlow.setOutEdges(outEdges);
        MyFlowRunnerManager myFlowRunnerManager = new MyFlowRunnerManager(myFlow);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"PoliceMan-Thread");
            }
        });

        System.out.println("PoliceOffice - Start startAlarmOffice ");
        scheduledExecutorService.scheduleAtFixedRate(myFlowRunnerManager, 0, 3, TimeUnit.SECONDS);
        System.out.println("启动完毕");
    }

}
