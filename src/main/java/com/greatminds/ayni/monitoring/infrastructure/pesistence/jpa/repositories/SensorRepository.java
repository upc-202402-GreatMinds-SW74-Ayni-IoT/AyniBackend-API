package com.greatminds.ayni.monitoring.infrastructure.pesistence.jpa.repositories;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findAllSensorsByCropId(Long cropId);
    Optional<Sensor> findSensorByCropId(Long cropId);
    Optional<Sensor> findSensorByIdAndCropId(Long id, Long cropId);
}
