package com.cloud.ventevoiture.model.entity.announces;

import com.cloud.ventevoiture.model.entity.user.Person;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

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
    @JoinColumn(name = "id_car")
    private Car car;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @Column(name = "validation_date")
    private LocalDate validationDate;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person person;

    @JsonProperty("status_intitule")
    public String getStatusString(){
        if(this.status<0) return "Supprimé";
        else if(this.status==0) return "En attente";
        else if(this.status==10) return "Validé";
        else if (this.status==20) return "Vendu";
        else return "Inconnu";
    }

}
