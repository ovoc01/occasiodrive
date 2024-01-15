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
    Integer idModel;
    Integer idTransmission;
    Integer idFuelType;
    Double enginePower;
    String registration;
    Integer manufacturingYear;
    Double mileAge;
}
