package com.thesis.vesselservice.controller;

import com.thesis.vesselservice.dto.VesselRequestDTO;
import com.thesis.vesselservice.dto.VesselResponseDTO;
import com.thesis.vesselservice.service.VesselService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vessels")
@RequiredArgsConstructor
public class VesselController {
    private final VesselService vesselService;
    @PostMapping()
    public ResponseEntity<VesselResponseDTO> createVessels(@RequestBody @Valid VesselRequestDTO dto){
        VesselResponseDTO vessel = vesselService.registerVessel(dto);
        return new ResponseEntity<>(vessel, HttpStatus.CREATED);
    }
    @GetMapping("/{imo}")
    public VesselResponseDTO getVessel(@PathVariable String imo){
        return vesselService.getVessel(imo);
    }
}
