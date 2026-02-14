package com.thesis.vesselservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "active")
public class Vessel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    //TODO проверка номера на целостность
    private String IMO;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotBlank
    private String flag;
    @Column(updatable = false, insertable = false)
    private boolean active;
}
