package mytest;

import azkaban.executor.Status;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lybuestc on 17/4/7.
 */
public class MyExecutableNode {
    private Status status = Status.READY;

    public String id;
    public String method;

    public MyExecuteFlowBase parentFlow;
    private Set<String> inNodes = new HashSet<String>();
    private Set<String> outNodes = new HashSet<String>();

    public MyExecutableNode() {
    }

    public MyExecutableNode(MyNode node) {
        this.id = node.getId();
        this.method = node.getMethod();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MyExecuteFlowBase getParentFlow() {
        return parentFlow;
    }

    public void setParentFlow(MyExecuteFlowBase parentFlow) {
        this.parentFlow = parentFlow;
    }

    public Set<String> getInNodes() {
        return inNodes;
    }

    public void setInNodes(Set<String> inNodes) {
        this.inNodes = inNodes;
    }

    public Set<String> getOutNodes() {
        return outNodes;
    }

    public void setOutNodes(Set<String> outNodes) {
        this.outNodes = outNodes;
    }
}
