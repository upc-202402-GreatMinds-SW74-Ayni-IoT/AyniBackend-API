package com.greatminds.ayni.monitoring.interfaces.rest;


import com.greatminds.ayni.monitoring.domain.model.commands.DeleteActuatorCommand;
import com.greatminds.ayni.monitoring.domain.model.queries.GetActuatorByIdQuery;
import com.greatminds.ayni.monitoring.domain.services.ActuatorCommandService;
import com.greatminds.ayni.monitoring.domain.services.ActuatorQueryService;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.ActuatorResource;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.CreateActuatorResource;
import com.greatminds.ayni.monitoring.interfaces.rest.resources.UpdateActuatorResource;
import com.greatminds.ayni.monitoring.interfaces.rest.transform.ActuatorResourceFromEntityAssembler;
import com.greatminds.ayni.monitoring.interfaces.rest.transform.CreateActuatorCommandFromResourceAssembler;
import com.greatminds.ayni.monitoring.interfaces.rest.transform.UpdateActuatorCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/v1/actuators", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Actuators", description = "Actuator Management Endpoints")
public class ActuatorController {
    private final ActuatorQueryService actuatorQueryService;
    private final ActuatorCommandService actuatorCommandService;

    public ActuatorController(ActuatorQueryService actuatorQueryService, ActuatorCommandService actuatorCommandService) {
        this.actuatorQueryService = actuatorQueryService;
        this.actuatorCommandService = actuatorCommandService;
    }

    @PostMapping
    public ResponseEntity<ActuatorResource> createActuator(@RequestBody CreateActuatorResource resource){
        var createActuatorCommand = CreateActuatorCommandFromResourceAssembler.toCommandFromResource(resource);

        var actuatorId = actuatorCommandService.handle(createActuatorCommand);

        if(actuatorId == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getActuatorByIdQuery = new GetActuatorByIdQuery(actuatorId);
        var actuator = actuatorQueryService.handle(getActuatorByIdQuery);

        if(actuator.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var actuatorResource = ActuatorResourceFromEntityAssembler.toResourceFromEntity(actuator.get());
        return new ResponseEntity<>(actuatorResource, HttpStatus.CREATED);
    }

    @GetMapping("/{actuatorId}")
    public ResponseEntity<ActuatorResource> getActuatorById(@PathVariable Long actuatorId) {
        var getActuatorByIdQuery = new GetActuatorByIdQuery(actuatorId);
        var actuator = actuatorQueryService.handle(getActuatorByIdQuery);

        if(actuator.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var actuatorResource = ActuatorResourceFromEntityAssembler.toResourceFromEntity(actuator.get());
        return new ResponseEntity<>(actuatorResource, HttpStatus.OK);
    }

    @PutMapping("/{actuatorId}")
    public ResponseEntity<ActuatorResource> updateActuator(@PathVariable Long actuatorId, @RequestBody UpdateActuatorResource resource){
        var updateActuatorCommand = UpdateActuatorCommandFromResourceAssembler.toCommandFromResource(actuatorId, resource);
        var updatedActuator = actuatorCommandService.handle(updateActuatorCommand);

        if(updatedActuator.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var actuatorResource = ActuatorResourceFromEntityAssembler.toResourceFromEntity(updatedActuator.get());
        return ResponseEntity.ok(actuatorResource);
    }

    @DeleteMapping("/{actuatorId}")
    public ResponseEntity<?> deleteActuator(@PathVariable Long actuatorId) {
        var deleteActuatorCommand = new DeleteActuatorCommand(actuatorId);
        actuatorCommandService.handle(deleteActuatorCommand);
        return ResponseEntity.ok("Actuator deleted successfully");
    }
}
