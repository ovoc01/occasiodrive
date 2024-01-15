package com.cloud.ventevoiture.model.car;

import com.cloud.ventevoiture.model.car.FuelType;
import com.cloud.ventevoiture.model.car.Transmission;
import com.cloud.ventevoiture.model.model.Model;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_model")
    private Model idModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transmission")
    private Transmission idTransmission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fuel_type")
    private FuelType idFuelType;

    @Column(name = "engine_power")
    private Double enginePower;

}