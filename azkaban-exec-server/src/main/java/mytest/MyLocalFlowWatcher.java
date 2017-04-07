package mytest;

import azkaban.event.Event;
import azkaban.event.EventListener;

/**
 * Created by lybuestc on 17/4/6.
 */
public class MyLocalFlowWatcher extends MyFlowWatcher {

    MyFlowRunner runner;
    public MyLocalFlowWatcher(MyFlowRunner runner) {
        this.runner = runner;
    }

    public class LocalFlowWatcherListener implements EventListener {

        @Override
        public void handleEvent(Event event) {
            if (event.getType() == Event.Type.JOB_FINISHED) {
                System.out.println("flow 完成了");
            }
        }
    }

}
