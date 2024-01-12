package com.cloud.ventevoiture.model.announces;

import com.cloud.ventevoiture.model.user.Person;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "favorite_announces")
@Entity 
public class FavoriteAnnounces {

    @Id
    Integer id_favorite;
    @ManyToOne
    @JoinColumn(name = "id_person")
    Person person;

    @ManyToOne
    @JoinColumn(name = "id_announces")
    Announces announces;

    public int getId_favorite() {
        return id_favorite;
    }

    public void setId_favorite(int id_favorite) {
        this.id_favorite = id_favorite;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Announces getAnnounces() {
        return announces;
    }

    public void setAnnounces(Announces announces) {
        this.announces = announces;
    }
    
    
}