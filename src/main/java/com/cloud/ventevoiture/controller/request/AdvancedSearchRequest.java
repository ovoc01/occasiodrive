package com.cloud.ventevoiture.controller.request;


import java.sql.Date;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvancedSearchRequest {
    String keyword;
    String dateAnnounceMin;
    String dateAnnounceMax;
    Integer category;
    Integer brand;
    Integer model;
    Integer transmission;
    Integer fuelType;
    Double enginePowerMin;
    Double enginePowerMax;
    Integer manufacturingYearMin;
    Integer manufacturingYearMax;
    Double mileAgeMin;
    Double mileAgeMax;
    Double sellingPriceMin;
    Double sellingPriceMax;
}
