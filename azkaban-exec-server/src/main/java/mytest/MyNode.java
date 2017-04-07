package mytest;

/**
 * Created by lybuestc on 17/4/6.
 */
public class MyNode{
    public String id;
    public String method;

    public MyNode(String id, String method) {
        this.id = id;
        this.method = method;
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
}
