package mytest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by lybuestc on 17/4/7.
 */
public class MyExecuteFlowBase extends MyExecutableNode{

    MyFlow flow ;

    public MyExecuteFlowBase(MyFlow flow) {
        this.flow = flow;
        setFlow();
    }

    private HashMap<String, MyExecutableNode> executableNodes =
            new HashMap<String, MyExecutableNode>();
    private ArrayList<String> startNodes;
    private ArrayList<String> endNodes;


    private void setFlow() {
        for(String nodeId:flow.getNodes().keySet()){
            MyExecutableNode executeableNode = new MyExecutableNode(flow.getNodes().get(nodeId));
            executableNodes.put(nodeId,executeableNode);
        }
        for(MyEdge edge:flow.getEdges().values()){
            String sourceNodeId = edge.getSource().getId();
            String targetNodeId = edge.getTarget().getId();
            executableNodes.get(sourceNodeId).getOutNodes().add(targetNodeId);//对一条边来说source节点的出节点加1
            executableNodes.get(targetNodeId).getInNodes().add(sourceNodeId);//对一条边来说target节点的入节点加1
        }
    }

    public MyFlow getFlow() {
        return flow;
    }

    public List<String> getStartNode(){
        if (startNodes == null) {
            startNodes = new ArrayList<String>();
            for (MyExecutableNode node : executableNodes.values()) {
                if (node.getInNodes().isEmpty()) {
                    startNodes.add(node.getId());
                }
            }
        }
        return startNodes;
    }


    public MyExecutableNode getExecutableNode(String startNodeId) {
        return executableNodes.get(startNodeId);
    }

    public HashMap<String, MyExecutableNode> getExecutableNodes() {
        return executableNodes;
    }

    public Set<String> getOutNodes(String nodeId){
        return executableNodes.get(nodeId).getOutNodes();
    }

    public Set<String> getInNodes(String nodeId){
        return executableNodes.get(nodeId).getInNodes();
    }
}
