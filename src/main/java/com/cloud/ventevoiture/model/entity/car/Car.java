package com.cloud.ventevoiture.model.entity.car;

import com.cloud.ventevoiture.model.entity.model.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_model")
    private Model idModel;

    @ManyToOne
    @JoinColumn(name = "id_transmission")
    private Transmission idTransmission;

    @ManyToOne
    @JoinColumn(name = "id_fuel_type")
    private FuelType idFuelType;

    @Column(name = "engine_power")
    private Double enginePower;

}