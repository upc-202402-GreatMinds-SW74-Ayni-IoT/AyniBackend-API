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
    private Long cropId;

    public Sensor() {
    }

    public Sensor(Float temperature, Float hydration, Float oxygenation, Long cropId) {
        this.temperature = temperature;
        this.oxygenation = oxygenation;
        this.hydration = hydration;
        this.cropId = cropId;
    }

    public Sensor update(Float temperature, Float hydration, Float oxygenation, Long cropId) {
        if (temperature != null) {
            this.temperature = temperature;
        }
        if (hydration != null) {
            this.hydration = hydration;
        }
        if (oxygenation != null) {
            this.oxygenation = oxygenation;
        }
        if (cropId != null) {
            this.cropId = cropId;
        }
        return this;
    }
}
