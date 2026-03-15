package com.thesis.vesselservice.controller;

import com.thesis.vesselservice.dto.VesselRequestDTO;
import com.thesis.vesselservice.dto.VesselResponseDTO;
import com.thesis.vesselservice.service.VesselService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Vessel management")
@RestController
@RequestMapping("/api/v1/vessels")
@RequiredArgsConstructor
public class VesselController {
    private final VesselService vesselService;
    @Operation(description = "Add new vessel to db")
    @PostMapping()
    public ResponseEntity<VesselResponseDTO> createVessels(@RequestBody @Valid VesselRequestDTO dto){
        VesselResponseDTO vessel = vesselService.registerVessel(dto);
        return new ResponseEntity<>(vessel, HttpStatus.CREATED);
    }
    @Operation(description = "Get vessel with required IMO")
    @GetMapping("/{imo}")
    public ResponseEntity<VesselResponseDTO> getVessel(@PathVariable String imo){
        return new ResponseEntity<>(vesselService.getVessel(imo), HttpStatus.OK);
    }
    @Operation(description = "Get all vessels")
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
    @Operation(description = "Update vessel data")
    @PatchMapping()
    public ResponseEntity<VesselResponseDTO> updateVessel(@RequestParam Long id, @RequestBody @Valid VesselRequestDTO dto){
        return new ResponseEntity<>(vesselService.updateVessel(id, dto), HttpStatus.OK);
    }
    @Operation(description = "Delete vessel")
    @DeleteMapping()
    public HttpStatus deleteVessel(@RequestParam Long id){
        vesselService.deleteVessel(id);
        return HttpStatus.OK;
    }
}