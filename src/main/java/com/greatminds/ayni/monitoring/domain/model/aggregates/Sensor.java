package com.greatminds.ayni.monitoring.domain.model.aggregates;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "sensors")
public class Sensor {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private Float temperature;

    @Getter
    private Float hydration;

    @Getter
    private Float oxygenation;

    @Getter
    private Float waterLevel;

    @Getter
    private Long cropId;

    public Sensor() {
    }

    public Sensor(Float temperature, Float hydration, Float oxygenation, Float waterLevel, Long cropId) {
        this.temperature = temperature;
        this.oxygenation = oxygenation;
        this.hydration = hydration;
        this.waterLevel = waterLevel;
        this.cropId = cropId;
    }

    public void update(Sensor request) {
        if (request.temperature != null) {
            this.temperature = request.temperature;
        }
        if (request.hydration != null) {
            this.hydration = request.hydration;
        }
        if (request.oxygenation != null) {
            this.oxygenation = request.oxygenation;
        }
        if (request.waterLevel != null) {
            this.waterLevel = request.waterLevel;
        }
        if (request.cropId != null) {
            this.cropId = request.cropId;
        }
    }

    public void updateValues(Float temperature, Float hydration, Float oxygenation, Float waterLevel) {
        if (temperature != null) {
            this.temperature = temperature;
        }
        if (hydration != null) {
            this.hydration = hydration;
        }
        if (oxygenation != null) {
            this.oxygenation = oxygenation;
        }
        if (waterLevel != null) {
            this.waterLevel = waterLevel;
        }
    }
}
