package com.cloud.ventevoiture.model.entity.announces;
import com.cloud.ventevoiture.model.entity.user.Person;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Table(name = "announces_picture")
@Entity 
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncesPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_picture;

    @ManyToOne
    @JoinColumn(name = "id_announce")
    @JsonBackReference
    Announce announce;

    @JsonIgnore
    byte[] imageByte;

    @JsonIgnoreProperties("pictures")

    public String getImage() {
        return new String(imageByte);
    }
    
}


