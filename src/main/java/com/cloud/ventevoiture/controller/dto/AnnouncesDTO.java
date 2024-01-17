package com.cloud.ventevoiture.controller.dto;

import com.cloud.ventevoiture.model.announces.Announce;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AnnouncesDTO {
    List<Announce> announces;
}
