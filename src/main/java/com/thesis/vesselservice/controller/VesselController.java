package com.thesis.vesselservice.controller;

import com.thesis.vesselservice.dto.VesselRequestDTO;
import com.thesis.vesselservice.dto.VesselResponseDTO;
import com.thesis.vesselservice.service.VesselService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<VesselResponseDTO> getVessel(@PathVariable String imo){
        return new ResponseEntity<>(vesselService.getVessel(imo), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<Page<VesselResponseDTO>> index(@RequestParam(name = "page", defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "10") int size,
                                                         @RequestParam(name = "type", required = false) String type,
                                                         @RequestParam(name = "name", required = false) String name){
        if (type != null && name != null){
            return ResponseEntity.ok(vesselService.findAllByTypeAndName(page, size, type, name));
        } else if (name != null) {
            return ResponseEntity.ok(vesselService.findAllByName(page, size, name));
        }
        else if (type != null) {
            return ResponseEntity.ok(vesselService.findAllByType(page, size, type));
        }
        return ResponseEntity.ok(vesselService.findAll(page, size));
    }
    @PatchMapping()
    public ResponseEntity<VesselResponseDTO> updateVessel(@RequestParam Long id, @RequestBody @Valid VesselRequestDTO dto){
        return new ResponseEntity<>(vesselService.updateVessel(id, dto), HttpStatus.OK);
    }
    @DeleteMapping()
    public HttpStatus deleteVessel(@RequestParam Long id){
        vesselService.deleteVessel(id);
        return HttpStatus.OK;
    }
}