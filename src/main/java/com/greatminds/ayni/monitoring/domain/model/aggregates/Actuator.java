package com.greatminds.ayni.monitoring.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "actuators")
public class Actuator {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String status;

    @Getter
    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    public Actuator() {
    }

    public Actuator(String status, Sensor sensor) {
        this.status = status;
        this.sensor = sensor;
    }

    public Actuator updateStatus(String status) {
        this.status = status;
        return this;
    }
}
