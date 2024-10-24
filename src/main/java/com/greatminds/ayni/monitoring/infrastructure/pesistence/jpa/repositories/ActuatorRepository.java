package com.greatminds.ayni.monitoring.infrastructure.pesistence.jpa.repositories;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Actuator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActuatorRepository extends JpaRepository<Actuator, Long> {
    List<Actuator> findAllActuatorsBySensorId(Long sensorId);
    Optional<Actuator> findActuatorByIdAndSensorId(Long id, Long sensorId);
}
