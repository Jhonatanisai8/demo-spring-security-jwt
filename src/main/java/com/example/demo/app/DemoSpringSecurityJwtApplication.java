package com.example.demo.app;

import com.example.demo.app.models.EnumRole;
import com.example.demo.app.models.RoleEntity;
import com.example.demo.app.models.UserEntity;
import com.example.demo.app.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class DemoSpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringSecurityJwtApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Bean
    CommandLineRunner init() {
        return args -> {
            UserEntity user01 = UserEntity.builder()
                    .emailUser("example@example.com")
                    .userName("jhos")
                    .passwordUser(passwordEncoder.encode("jhos"))
                    .roles(Set.of(RoleEntity.builder()
                            .role(EnumRole.valueOf(EnumRole.ADMIN.name()))
                            .build()))
                    .build();
            UserEntity user02 = UserEntity.builder()
                    .emailUser("example@example.com")
                    .userName("anyi")
                    .passwordUser(passwordEncoder.encode("anyi"))
                    .roles(Set.of(RoleEntity.builder()
                            .role(EnumRole.valueOf(EnumRole.USER.name()))
                            .build()))
                    .build();
            UserEntity user03 = UserEntity.builder()
                    .emailUser("example@example.com")
                    .userName("andre")
                    .passwordUser(passwordEncoder.encode("andre"))
                    .roles(Set.of(RoleEntity.builder()
                            .role(EnumRole.valueOf(EnumRole.INVITED.name()))
                            .build()))
                    .build();
            userRepository.save(user01);
            userRepository.save(user02);
            userRepository.save(user03);
        };
    }

}
