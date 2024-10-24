package com.greatminds.ayni.monitoring.application.internal.queryservices;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Actuator;
import com.greatminds.ayni.monitoring.domain.model.queries.GetActuatorByIdAndSensorIdQuery;
import com.greatminds.ayni.monitoring.domain.model.queries.GetActuatorByIdQuery;
import com.greatminds.ayni.monitoring.domain.model.queries.GetAllActuatorsBySensorIdQuery;
import com.greatminds.ayni.monitoring.domain.services.ActuatorQueryService;
import com.greatminds.ayni.monitoring.infrastructure.pesistence.jpa.repositories.ActuatorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActuatorQueryServiceImpl implements ActuatorQueryService {
    private final ActuatorRepository actuatorRepository;

    public ActuatorQueryServiceImpl(ActuatorRepository actuatorRepository) {
        this.actuatorRepository = actuatorRepository;
    }

    @Override
    public List<Actuator> handle(GetAllActuatorsBySensorIdQuery query) {
        return this.actuatorRepository.findAllActuatorsBySensorId(query.sensorId());
    }

    @Override
    public Optional<Actuator> handle(GetActuatorByIdAndSensorIdQuery query) {
        return this.actuatorRepository.findActuatorByIdAndSensorId(query.id(), query.sensorId());
    }

    @Override
    public Optional<Actuator> handle(GetActuatorByIdQuery query) {
        return this.actuatorRepository.findById(query.id());
    }
}
