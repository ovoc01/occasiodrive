package com.cloud.ventevoiture.model.entity.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idPerson;
    String lastName;
    String firstName;
    Date birthDate;
    @Enumerated(EnumType.ORDINAL)
    Gender gender;

}
