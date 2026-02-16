package com.thesis.vesselservice.repository;

import com.thesis.vesselservice.model.Vessel;
import jakarta.validation.constraints.NotEmpty;
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
    @Modifying // Обязательно для запросов на изменение (UPDATE/DELETE)
    @Query(value = "UPDATE vessel SET active = true WHERE imo = :imo", nativeQuery = true)
    void activateByImo(@Param("imo") String imo);
}
