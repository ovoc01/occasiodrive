package com.cloud.ventevoiture.model.car;

import com.cloud.ventevoiture.model.model.Model;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "car")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "registration", length = 50)
    private String registration;

    @Column(name = "manufacturing_year")
    private Integer manufacturingYear;

    @Column(name = "mile_age")
    private Double mileAge;

}