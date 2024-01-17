package com.cloud.ventevoiture.model.entity.announces;

import com.cloud.ventevoiture.model.entity.user.Person;
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

    @ManyToOne
    @JoinColumn(name = "id_car_announce")
    private CarAnnounce idCarAnnounce;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @Column(name = "validation_date")
    private LocalDate validationDate;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person idPerson;

}