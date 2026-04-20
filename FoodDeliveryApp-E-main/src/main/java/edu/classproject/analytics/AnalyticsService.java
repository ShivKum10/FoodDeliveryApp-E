package edu.classproject.analytics;

import java.util.List;

public interface AnalyticsService {
    List<MetricSnapshot> dailySummary();
}
