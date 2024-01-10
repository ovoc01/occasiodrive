package com.cloud.ventevoiture.controller.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncesRequest {
    String description;
    String dateAnnounces;
    Double sellingPrice;
}
