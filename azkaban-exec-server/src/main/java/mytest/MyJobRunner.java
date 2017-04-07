package mytest;

import java.lang.reflect.Method;

/**
 * Created by lybuestc on 17/4/7.
 */
public class MyJobRunner extends MyEventHandler implements Runnable{

    private MyExecutableNode node;

    public MyExecutableNode getNode() {
        return node;
    }

    public void setNode(MyExecutableNode node) {
        this.node = node;
    }

    @Override
    public void run() {
        String jobClass = "mytest.executeClass.ExecuteClass";
        try {
            execute(jobClass);
        } catch (Exception e) {
            System.out.println("exception " + e);
        }
        this.fireEventListeners(MyEvent.create(this, MyEvent.Type.JOB_FINISHED));
    }

    public void execute(String jobClass) throws Exception {
        Class clazz = Class.forName(jobClass);
        Object obj = clazz.newInstance();
        Method method = clazz.getMethod(node.getMethod());
        method.invoke(obj);//返回值为该方法执行的返回值
    }
}
