package com.thesis.vesselservice.service;

import com.thesis.vesselservice.dto.VesselRequestDTO;
import com.thesis.vesselservice.dto.VesselResponseDTO;
import com.thesis.vesselservice.exception.EntityAlreadyExistException;
import com.thesis.vesselservice.exception.EntityNotFoundException;
import com.thesis.vesselservice.model.Vessel;
import com.thesis.vesselservice.repository.VesselRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VesselService {
    private final VesselRepository vesselRepository;
    private final ModelMapper modelMapper;
    @Transactional
    public VesselResponseDTO registerVessel(VesselRequestDTO vesselRequestDTO){
        if (vesselRepository.findVesselByIMO(vesselRequestDTO.getIMO()).isEmpty()){
            Vessel vessel = modelMapper.map(vesselRequestDTO, Vessel.class);
            vesselRepository.save(vessel);
            return modelMapper.map(vessel, VesselResponseDTO.class);
        }
        else {
            throw new EntityAlreadyExistException();
        }
    }
    public VesselResponseDTO getVessel(String imo){
        Vessel vessel = vesselRepository.findVesselByIMO(imo).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(vessel, VesselResponseDTO.class);
    }
    //TODO сделать enrich вместо подставки id и сделать проверку IMO поумнее как и апдейт в общем
    @Transactional
    public void updateVessel(Long id, VesselRequestDTO dto){
        if (vesselRepository.findVesselByIMO(dto.getIMO()).isEmpty()){
            Vessel vessel = modelMapper.map(dto, Vessel.class);
            vessel.setId(id);
            vesselRepository.save(vessel);
        }
        else {
            throw new EntityAlreadyExistException();
        }
    }
}
