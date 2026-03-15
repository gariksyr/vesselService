package com.thesis.vesselservice.dto;

import com.thesis.vesselservice.util.ValidImo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VesselRequestDTO {
    @NotBlank
    @Schema(description = "Unique 7-digit vessel code", example = "9311622", requiredMode = Schema.RequiredMode.REQUIRED)
    @ValidImo
    private String IMO;
    @NotBlank
    @Schema(description = "Vessel's name", example = "Igor")
    private String name;
    @NotBlank
    @Schema(description = "Type of vessel", example = "Tanker")
    private String type;
    @NotBlank
    @Schema(description = "Vessel's country", example = "Russia")
    private String flag;
}
