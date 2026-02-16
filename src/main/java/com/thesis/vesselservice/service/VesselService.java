package com.thesis.vesselservice.service;

import com.thesis.vesselservice.dto.VesselRequestDTO;
import com.thesis.vesselservice.dto.VesselResponseDTO;
import com.thesis.vesselservice.exception.EntityAlreadyExistException;
import com.thesis.vesselservice.exception.EntityNotFoundException;
import com.thesis.vesselservice.model.Vessel;
import com.thesis.vesselservice.repository.VesselRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VesselService {
    private final VesselRepository vesselRepository;
    private final ModelMapper modelMapper;
    @Transactional
    //TODO есть вопросики по поводу восстановления данных если они отличные от тех что в бд: что восстанавливать старые, или изменять на отправленные
    public VesselResponseDTO registerVessel(VesselRequestDTO vesselRequestDTO){
        Optional<Vessel> findVessel = vesselRepository.findVesselByIMO(vesselRequestDTO.getIMO());
       // Vessel newVessel = modelMapper.map(vesselRequestDTO, Vessel.class);
        Vessel vessel;
        if (findVessel.isEmpty()){
            vessel = modelMapper.map(vesselRequestDTO, Vessel.class);
            vesselRepository.save(vessel);
        }
        else {
            vessel = findVessel.get();
            if (vessel.isActive()) {
                throw new EntityAlreadyExistException();
            }
            else {
                vesselRepository.activateByImo(vessel.getIMO());
//                Vessel oldVessel = vesselRepository.findVesselByIMO(vessel.getIMO()).orElseThrow(EntityNotFoundException::new);
//                modelMapper.map(newVessel, oldVessel);
//                vesselRepository.save(oldVessel);
            }
        }
        return modelMapper.map(vessel, VesselResponseDTO.class);
    }
    public VesselResponseDTO getVessel(String imo){
        Vessel vessel = vesselRepository.findVesselByIMO(imo).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(vessel, VesselResponseDTO.class);
    }
    @Transactional
    public VesselResponseDTO updateVessel(Long id, VesselRequestDTO dto){
            Vessel vessel = vesselRepository.findVesselById(id).orElseThrow(EntityNotFoundException::new);
            Vessel newVessel = modelMapper.map(dto, Vessel.class);
            if (!newVessel.getIMO().equals(vessel.getIMO())) {
                if (vesselRepository.findVesselByIMO(newVessel.getIMO()).isPresent()) {
                    throw new EntityAlreadyExistException();
                }
            }
            modelMapper.map(newVessel, vessel);
            vessel.setId(id);
            vesselRepository.save(vessel);
            return modelMapper.map(vessel, VesselResponseDTO.class);
    }
    public Page<VesselResponseDTO> findAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return vesselRepository.findAll(pageable)
                .map(vessel -> modelMapper.map(vessel, VesselResponseDTO.class));
    }
    public Page<VesselResponseDTO> findAllByTypeAndName(int page, int size, String type, String name){
        Pageable pageable = PageRequest.of(page, size);
        return vesselRepository.findAllByTypeAndName(pageable, type, name)
                .map(vessel -> modelMapper.map(vessel, VesselResponseDTO.class));
    }
    public Page<VesselResponseDTO> findAllByType(int page, int size, String type){
        Pageable pageable = PageRequest.of(page, size);
        return vesselRepository.findAllByType(pageable, type)
                .map(vessel -> modelMapper.map(vessel, VesselResponseDTO.class));
    }
    public Page<VesselResponseDTO> findAllByName(int page, int size, String name){
        Pageable pageable = PageRequest.of(page, size);
        return vesselRepository.findAllByName(pageable, name)
                .map(vessel -> modelMapper.map(vessel, VesselResponseDTO.class));
    }
    @Transactional
    public void deleteVessel(Long id){
        Vessel vessel = vesselRepository.findVesselById(id).orElseThrow(EntityNotFoundException::new);
        vesselRepository.delete(vessel);
    }
}
