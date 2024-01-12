package com.cloud.ventevoiture.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String fullName;
    private String email;
    private String password;
    private Integer gender;
    private String birthDate;
}
