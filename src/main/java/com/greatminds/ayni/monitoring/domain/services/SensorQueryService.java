package com.greatminds.ayni.monitoring.domain.services;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Sensor;
import com.greatminds.ayni.monitoring.domain.model.queries.GetAllSensorsByCropIdQuery;
import com.greatminds.ayni.monitoring.domain.model.queries.GetSensorByIdAndCropIdQuery;
import com.greatminds.ayni.monitoring.domain.model.queries.GetSensorByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SensorQueryService {
    List<Sensor> handle(GetAllSensorsByCropIdQuery query);
    Optional<Sensor> handle(GetSensorByIdAndCropIdQuery query);
    Optional<Sensor> handle(GetSensorByIdQuery query);
}
