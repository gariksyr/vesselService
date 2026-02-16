package com.thesis.vesselservice.repository;

import com.thesis.vesselservice.model.Vessel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VesselRepository extends JpaRepository<Vessel, Long> {
    @Query(value = "SELECT * FROM vessel WHERE imo = :imo", nativeQuery = true)
    Optional<Vessel> findVesselByIMO(@NotEmpty @Param("imo") String imo);

    Optional<Vessel> findVesselById(Long id);
    @Modifying
    @Query(value = "UPDATE vessel SET active = true WHERE imo = :imo", nativeQuery = true)
    void activateByImo(@Param("imo") String imo);

    Page<Vessel> findAllByName(Pageable pageable, @NotBlank String name);

    Page<Vessel> findAllByType(Pageable pageable,@NotBlank String type);

    Page<Vessel> findAllByTypeAndName(Pageable pageable, @NotBlank String type, @NotBlank String name);
}
