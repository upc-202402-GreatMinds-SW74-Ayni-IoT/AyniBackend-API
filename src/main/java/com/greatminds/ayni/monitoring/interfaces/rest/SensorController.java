package com.greatminds.ayni.monitoring.interfaces.rest;

import com.greatminds.ayni.monitoring.domain.model.aggregates.Sensor;
import com.greatminds.ayni.monitoring.domain.model.queries.GetSensorByIdQuery;
import com.greatminds.ayni.monitoring.domain.services.SensorCommandService;
import com.greatminds.ayni.monitoring.domain.services.SensorQueryService;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.CreateSensorResource;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.SensorResource;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.UpdateSensorResource;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.UpdateSensorValuesResource;
import com.greatminds.ayni.monitoring.interfaces.rest.transform.CreateSensorCommandFromResourceAssembler;
import com.greatminds.ayni.monitoring.interfaces.rest.transform.SensorResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/sensors", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sensors", description = "Sensor Management Endpoints")
public class SensorController {
    private final SensorQueryService sensorQueryService;
    private final SensorCommandService sensorCommandService;


    public SensorController(SensorQueryService sensorQueryService, SensorCommandService sensorCommandService) {
        this.sensorQueryService = sensorQueryService;
        this.sensorCommandService = sensorCommandService;
    }

    @PostMapping
    public ResponseEntity<SensorResource> createSensor(@RequestBody CreateSensorResource resource){
        var createSensorCommand = CreateSensorCommandFromResourceAssembler.toCommandFromResource(resource);

        var sensorId = sensorCommandService.handle(createSensorCommand);

        if(sensorId == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getSensorByIdQuery = new GetSensorByIdQuery(sensorId);
        var sensor = sensorQueryService.handle(getSensorByIdQuery);

        if(sensor.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var sensorResource = SensorResourceFromEntityAssembler.toResourceFromEntity(sensor.get());
        return new ResponseEntity<>(sensorResource, HttpStatus.CREATED);
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<SensorResource> getSensorById(@PathVariable Long sensorId) {
        var getSensorByIdQuery = new GetSensorByIdQuery(sensorId);
        var sensor = sensorQueryService.handle(getSensorByIdQuery);

        if(sensor.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var sensorResource = SensorResourceFromEntityAssembler.toResourceFromEntity(sensor.get());
        return ResponseEntity.ok(sensorResource);
    }

    @PutMapping("/{sensorId}")
    public ResponseEntity<SensorResource> updateSensor(@PathVariable Long sensorId, @RequestBody UpdateSensorResource resource){
        try{
            Long updatedSensorId = sensorCommandService.updateSensor(sensorId, resource);
            Sensor updatedSensor = sensorQueryService.handle(new GetSensorByIdQuery(updatedSensorId))
                    .orElseThrow(() -> new IllegalArgumentException("Sensor not found"));
            return ResponseEntity.ok(SensorResourceFromEntityAssembler.toResourceFromEntity(updatedSensor));
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{cropId}/values")
    public ResponseEntity<SensorResource> updateSensorValues(@PathVariable Long cropId, @RequestBody UpdateSensorValuesResource updateSensorValuesResource){
        try{
            Long updatedSensorId = sensorCommandService.updateSensorValues(cropId, updateSensorValuesResource);
            Sensor updatedSensor = sensorQueryService.handle(new GetSensorByIdQuery(updatedSensorId))
                    .orElseThrow(() -> new IllegalArgumentException("Sensor not found"));
            return ResponseEntity.ok(SensorResourceFromEntityAssembler.toResourceFromEntity(updatedSensor));
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{sensorId}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long sensorId) {
        var getSensorByIdQuery = new GetSensorByIdQuery(sensorId);
        var existingSensor = sensorQueryService.handle(getSensorByIdQuery);

        if(existingSensor.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        sensorCommandService.deleteSensor(sensorId);
        return ResponseEntity.noContent().build();
    }
}
