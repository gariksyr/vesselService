package com.thesis.vesselservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Vessel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    //TODO проверка номера на целостность
    private String IMO;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotBlank
    private String flag;

//    private boolean deleted = false;
}
