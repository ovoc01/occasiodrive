package com.cloud.ventevoiture.controller.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncesRequest {
    String description;
    Double sellingPrice;
    String[] base64Images;
    Integer idBrand;
    Integer idModel;
    Integer idCategory;
    Integer idMotorisation;
    Integer idTransmission;
    Integer idVersion;
    Integer idFuelType;
    String registration;
    Double mileAge;
}
