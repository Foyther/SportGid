package android.kfu.service.api.response;

import android.kfu.entities.Event;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Nurislam on 15.05.2018.
 */
public class MyEventsResult implements Serializable {

    private Set<EventInfoResult> events;

    public MyEventsResult() {
    }

    public Set<EventInfoResult> getEvents() {
        return events;
    }

    public void setEvents(Set<EventInfoResult> events) {
        this.events = events;
    }
}
