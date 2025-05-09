package com.example.demo.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {

    @GetMapping(path = "/accessAdmin")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String accessAdmin() {
        return "Hola has accedido con rol de de ADMIN";
    }

    @GetMapping(path = "/accessUser")
    @PreAuthorize("hasRole('USER')")
    public String accessUser() {
        return "Hola has accedido con rol de de USER";
    }

    @GetMapping(path = "/accessInvited")
    @PreAuthorize("hasRole('INVITED')")
    public String accessInvited() {
        return "Hola has accedido con rol de de INVITED";
    }
}
