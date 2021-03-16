package com.gjm.shelterbrainbackend.animal;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Nazwa jest wymagana")
    @NotEmpty(message = "Nazwa nie może być pusta")
    @NotBlank(message = "Nazwa nie może być znakiem białym")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Płeć jest wymagana")
    private Gender gender;

    @NotNull(message = "Wiek jest wymagany")
    @Min(value = 1, message = "Wiek nie może być mniejszy od 1")
    @Max(value = 100, message = "Wiek nie może być większy od 100")
    private int age;

    @Lob
    @NotNull(message = "Zdjęcie profilowe jest wymagane")
    @NotEmpty(message = "Zdjęcie profilowe nie może być puste")
    private String base64Image;

    @CreatedDate
    private LocalDateTime dateOfArrival;
}
