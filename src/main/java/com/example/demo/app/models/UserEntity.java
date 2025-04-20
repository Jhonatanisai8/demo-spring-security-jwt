package com.example.demo.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @NotBlank
    @Email
    @Size(max = 80)
    private String emailUser;

    @NotBlank
    @Size(max = 80)
    private String userName;

    @NotBlank
    private String passwordUser;
}
