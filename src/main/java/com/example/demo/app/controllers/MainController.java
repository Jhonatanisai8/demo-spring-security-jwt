package com.example.demo.app.controllers;

import com.example.demo.app.controllers.requests.CreatedUserDto;
import com.example.demo.app.models.EnumRole;
import com.example.demo.app.models.RoleEntity;
import com.example.demo.app.models.UserEntity;
import com.example.demo.app.repositorys.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello World not Security";
    }

    @RequestMapping(path = "/helloSecurity", method = RequestMethod.GET)
    public String helloSecurity() {
        return "Hello Spring-Security";
    }

    @PostMapping(path = "/createdUser")
    public ResponseEntity<?> createdUser(@Valid @RequestBody CreatedUserDto requestUser) {
        UserEntity user = getUserEntity(requestUser);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(path = "/deleteUser")
    public String deleteUser(@RequestParam(name = "id") Integer userId) {
        userRepository.deleteById(userId);
        return "Deleted User " + userId;
    }

    private static UserEntity getUserEntity(CreatedUserDto requestUser) {
        Set<RoleEntity> roles = requestUser.getRoles()
                .stream()
                .map(role -> RoleEntity.builder()
                        .role(EnumRole.valueOf(role))
                        .build()
                ).collect(Collectors.toSet());

        UserEntity user = UserEntity.builder()
                .userName(requestUser.getUserName())
                .emailUser(requestUser.getEmailUser())
                .passwordUser(requestUser.getPasswordUser())
                .roles(roles)
                .build();
        return user;
    }


}
