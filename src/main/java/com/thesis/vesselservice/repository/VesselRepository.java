package com.thesis.vesselservice.repository;

import com.thesis.vesselservice.model.Vessel;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VesselRepository extends JpaRepository<Vessel, Long> {
    Optional<Vessel> findVesselByIMO(@NotEmpty String imo);
}
