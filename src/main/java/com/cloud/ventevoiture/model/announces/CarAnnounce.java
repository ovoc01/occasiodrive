package com.cloud.ventevoiture.model.announces;

import com.cloud.ventevoiture.model.car.Car;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car_announces")
public class CarAnnounce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car_announces", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_car")
    private Car idCar;

    @Column(name = "registration", length = 50)
    private String registration;

    @Column(name = "manufacturing_year")
    private Integer manufacturingYear;

    @Column(name = "mile_age")
    private Double mileAge;

}