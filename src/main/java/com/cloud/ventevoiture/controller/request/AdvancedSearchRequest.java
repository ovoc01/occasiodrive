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
    Instant DateAnnounceMin;
    Instant DateAnnounceMax;
    String category;
    String brand;
    String model;
    String transmission;
    String fuelType;
    Double enginePowerMin;
    Double enginePowerMax;
    Integer manufacturingYearMin;
    Integer manufacturingYearMax;
    Double mileAgeMin;
    Double mileAgeMax;
    Double sellingPriceMin;
    Double sellingPriceMax;
}
