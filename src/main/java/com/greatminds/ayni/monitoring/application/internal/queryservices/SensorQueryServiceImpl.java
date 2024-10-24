package com.greatminds.ayni.monitoring.application.internal.queryservices;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Sensor;
import com.greatminds.ayni.monitoring.domain.model.queries.GetAllSensorsByCropIdQuery;
import com.greatminds.ayni.monitoring.domain.model.queries.GetSensorByIdAndCropIdQuery;
import com.greatminds.ayni.monitoring.domain.model.queries.GetSensorByIdQuery;
import com.greatminds.ayni.monitoring.domain.services.SensorQueryService;
import com.greatminds.ayni.monitoring.infrastructure.pesistence.jpa.repositories.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorQueryServiceImpl implements SensorQueryService {
    private final SensorRepository sensorRepository;

    public SensorQueryServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public List<Sensor> handle(GetAllSensorsByCropIdQuery query) {
        return this.sensorRepository.findAllSensorsByCropId(query.cropId());
    }

    @Override
    public Optional<Sensor> handle(GetSensorByIdAndCropIdQuery query) {
        return this.sensorRepository.findSensorByIdAndCropId(query.sensorId(), query.cropId());
    }

    @Override
    public Optional<Sensor> handle(GetSensorByIdQuery query) {
        return this.sensorRepository.findById(query.id());
    }
}
