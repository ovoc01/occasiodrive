package com.cloud.ventevoiture.model.entity.announces;
import com.cloud.ventevoiture.model.entity.user.Person;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "announces_picture")
@Entity 
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncesPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_picture;

    @ManyToOne
    @JoinColumn(name = "id_announce")
    Announce announce;

    byte[] imagebyte; 
    
}