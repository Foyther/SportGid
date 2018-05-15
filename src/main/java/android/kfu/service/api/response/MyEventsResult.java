package android.kfu.service.api.response;

import android.kfu.entities.Event;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Nurislam on 15.05.2018.
 */
public class MyEventsResult implements Serializable {

    private Set<Event> events;

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
