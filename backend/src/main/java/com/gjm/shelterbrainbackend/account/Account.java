package com.gjm.shelterbrainbackend.account;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Nazwa jest wymagana")
    @NotEmpty(message = "Nazwa nie może być pusta")
    @NotBlank(message = "Nazwa nie może być znakiem białym")
    private String name;

    @NotNull(message = "Hasło jest wymagane")
    @NotEmpty(message = "Hasło nie może być puste")
    @NotBlank(message = "Hasło nie może być znakiem białym")
    private String password;        // bcrypt

    @NotNull(message = "Email jest wymagany")
    @NotEmpty(message = "Email nie może być pusty")
    @Email(message = "Email musi być poprawny")
    private String email;

    @Lob
    @NotNull(message = "Zdjęcie profilowe jest wymagane")
    @NotEmpty(message = "Zdjęcie profilowe nie może być puste")
    private String base64Image;

    @CreatedDate
    private LocalDateTime dateOfAdd;
}
