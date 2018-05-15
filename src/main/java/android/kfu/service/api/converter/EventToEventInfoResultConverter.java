package android.kfu.service.api.converter;

import android.kfu.entities.Event;
import android.kfu.service.api.exception.NotFound.EventNotFoundException;
import android.kfu.service.api.response.EventInfoResult;

/**
 * Created by Nurislam on 15.05.2018.
 */
public interface EventToEventInfoResultConverter {
    public EventInfoResult getEventInfoResult(Event event) throws EventNotFoundException;
}
