package mytest;

/**
 * Created by lybuestc on 17/4/6.
 */
public class MyEdge {

    private MyNode source;
    private MyNode target;

    public MyEdge(MyNode source, MyNode target) {
        this.source = source;
        this.target = target;
    }

    public MyNode getSource() {
        return source;
    }

    public void setSource(MyNode source) {
        this.source = source;
    }

    public MyNode getTarget() {
        return target;
    }

    public void setTarget(MyNode target) {
        this.target = target;
    }
}
