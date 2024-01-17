package com.cloud.ventevoiture.model.entity.announces;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "announces_log")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncesLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_announces_log", nullable = false)
    private Integer id;

    @Column(name = "id_announce")
    private Integer idAnnounce;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "id_person")
    private Integer idPerson;

    @Column(name = "date")
    private LocalDate date;

}