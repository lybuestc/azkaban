package mytest;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by lybuestc on 17/4/6.
 */
public class MyFlow{
    private HashMap<String, MyNode> nodes = new HashMap<String, MyNode>();
    private HashMap<String, HashSet<MyEdge>> outEdges = new HashMap<String, HashSet<MyEdge>>();
    private HashMap<String, HashSet<MyEdge>> inEdges = new HashMap<String, HashSet<MyEdge>>();
    private HashMap<String, MyEdge> edges = new HashMap<String, MyEdge>();

    public HashMap<String, MyNode> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<String, MyNode> nodes) {
        this.nodes = nodes;
    }

    public HashMap<String, HashSet<MyEdge>> getOutEdges() {
        return outEdges;
    }

    public void setOutEdges(HashMap<String, HashSet<MyEdge>> outEdges) {
        this.outEdges = outEdges;
    }

    public HashMap<String, HashSet<MyEdge>> getInEdges() {
        return inEdges;
    }

    public void setInEdges(HashMap<String, HashSet<MyEdge>> inEdges) {
        this.inEdges = inEdges;
    }

    public HashMap<String, MyEdge> getEdges() {
        return edges;
    }

    public void setEdges(HashMap<String, MyEdge> edges) {
        this.edges = edges;
    }
}
