package mytest;

import azkaban.event.EventListener;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by lybuestc on 17/4/6.
 */
public class MyEventHandler {
    private HashSet<MyEventListener> listeners = new HashSet<MyEventListener>();

    public MyEventHandler() {
    }

    public void addListener(MyEventListener listener) {
        listeners.add(listener);
    }

    public void fireEventListeners(MyEvent event) {
        ArrayList<MyEventListener> listeners =
                new ArrayList<MyEventListener>(this.listeners);
        for (MyEventListener listener : listeners) {
            listener.handleEvent(event);
        }
    }

    public void removeListener(EventListener listener) {
        listeners.remove(listener);
    }
}
