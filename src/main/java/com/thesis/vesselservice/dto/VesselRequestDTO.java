package com.thesis.vesselservice.dto;

import com.thesis.vesselservice.util.ValidImo;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VesselRequestDTO {
    @NotBlank
    @ValidImo
    private String IMO;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotBlank
    private String flag;
}
