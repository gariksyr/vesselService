package com.thesis.vesselservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VesselRequestDTO {
    @NotBlank
    private String IMO;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotBlank
    private String flag;
}
