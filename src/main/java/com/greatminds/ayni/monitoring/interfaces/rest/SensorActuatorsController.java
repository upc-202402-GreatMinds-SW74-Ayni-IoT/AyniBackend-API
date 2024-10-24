package com.greatminds.ayni.monitoring.interfaces.rest;

import com.greatminds.ayni.monitoring.domain.model.queries.GetActuatorByIdAndSensorIdQuery;
import com.greatminds.ayni.monitoring.domain.model.queries.GetAllActuatorsBySensorIdQuery;
import com.greatminds.ayni.monitoring.domain.services.ActuatorQueryService;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.ActuatorResource;
import com.greatminds.ayni.monitoring.interfaces.rest.transform.ActuatorResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/sensors/{sensorId}/actuators", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sensors")
public class SensorActuatorsController {
    private final ActuatorQueryService actuatorQueryService;

    public SensorActuatorsController(ActuatorQueryService actuatorQueryService) {
        this.actuatorQueryService = actuatorQueryService;
    }

    /**
     * Handles the get of an actuator.
     * @param sensorId The sensor ID.
     * @return The actuators of the sensor.
     */
    @GetMapping
    public ResponseEntity<List<ActuatorResource>> getAllActuatorsBySensorId(@PathVariable Long sensorId) {
        var getAllActuatorsBySensorIdQuery = new GetAllActuatorsBySensorIdQuery(sensorId);
        var actuators = actuatorQueryService.handle(getAllActuatorsBySensorIdQuery);
        var actuatorsResource = actuators.stream().map(ActuatorResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(actuatorsResource);
    }

    /**
     * Handles the get of an actuator.
     * @param actuatorId The actuator ID.
     * @param sensorId The sensor ID.
     * @return The ID of the actuator.
     */
    @GetMapping("/{actuatorId}")
    public ResponseEntity<ActuatorResource> getActuatorByIdAndSensorId(@PathVariable Long sensorId, @PathVariable Long actuatorId) {
        var getActuatorByIdAndSensorIdQuery = new GetActuatorByIdAndSensorIdQuery(actuatorId, sensorId);
        var actuator = actuatorQueryService.handle(getActuatorByIdAndSensorIdQuery);

        if(actuator.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var actuatorResource = ActuatorResourceFromEntityAssembler.toResourceFromEntity(actuator.get());
        return ResponseEntity.ok(actuatorResource);
    }
}
