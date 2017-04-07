package mytest;

/**
 * Created by lybuestc on 17/4/6.
 */

public class MyEvent {
    public enum Type {
        FLOW_STARTED,
        FLOW_FINISHED,
        JOB_STARTED,
        JOB_FINISHED,
        JOB_STATUS_CHANGED,
        EXTERNAL_FLOW_UPDATED,
        EXTERNAL_JOB_UPDATED
    }

    private final Object runner;
    private final Type type;
    private final long time;

    private MyEvent(Object runner, Type type) {
        this.runner = runner;
        this.type = type;
        this.time = System.currentTimeMillis();
    }

    public Object getRunner() {
        return runner;
    }

    public Type getType() {
        return type;
    }

    public long getTime() {
        return time;
    }

    public static MyEvent create(Object runner, MyEvent.Type type) throws NullPointerException {
        return new MyEvent(runner, type);
    }

}
