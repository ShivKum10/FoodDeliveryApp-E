package edu.classproject.tracking;

import java.util.List;

public interface TrackingService {
    void addEvent(TrackingEvent event);

    List<TrackingEvent> timeline(String orderId);
}
