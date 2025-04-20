package com.example.demo.app.controllers.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatedUserDto {

    @Email
    @NotBlank
    private String emailUser;

    @NotBlank
    private String userName;

    @NotBlank
    private String passwordUser;

    private Set<String> roles;
}
