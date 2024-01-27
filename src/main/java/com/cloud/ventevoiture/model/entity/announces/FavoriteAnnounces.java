package com.cloud.ventevoiture.model.entity.announces;

import com.cloud.ventevoiture.model.entity.user.Person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "favorite_announces")
@Entity 
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteAnnounces {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_favorite;
    @ManyToOne
    @JoinColumn(name = "id_person")
    Person person;

    @ManyToOne
    @JoinColumn(name = "id_announces")
    Announce announces;

    
    
}