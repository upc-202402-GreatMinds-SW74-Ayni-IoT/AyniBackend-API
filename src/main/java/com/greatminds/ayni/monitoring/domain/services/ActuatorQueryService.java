package com.greatminds.ayni.monitoring.domain.services;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Actuator;
import com.greatminds.ayni.monitoring.domain.model.queries.GetActuatorByIdAndSensorIdQuery;
import com.greatminds.ayni.monitoring.domain.model.queries.GetActuatorByIdQuery;
import com.greatminds.ayni.monitoring.domain.model.queries.GetAllActuatorsBySensorIdQuery;

import java.util.List;
import java.util.Optional;

public interface ActuatorQueryService {
    List<Actuator> handle(GetAllActuatorsBySensorIdQuery query);
    Optional<Actuator> handle(GetActuatorByIdAndSensorIdQuery query);
    Optional<Actuator> handle(GetActuatorByIdQuery query);
}
