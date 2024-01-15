package com.cloud.ventevoiture.model.announces;

import com.cloud.ventevoiture.model.car.Car;
import com.cloud.ventevoiture.model.user.Person;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "announces")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Announce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_announces", nullable = false)
    private Integer id;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "date_announces")
    private Instant dateAnnounces;

    @Column(name = "status")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_car")
    private Car idCar;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @Column(name = "validation_date")
    private LocalDate validationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person")
    private Person idPerson;


}