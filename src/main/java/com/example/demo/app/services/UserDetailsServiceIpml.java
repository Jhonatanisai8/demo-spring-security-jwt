package com.example.demo.app.services;

import com.example.demo.app.models.UserEntity;
import com.example.demo.app.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceIpml
        implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username ".concat(username).concat(" no exist")));


        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
                .stream()
                .map(roleEntity -> new SimpleGrantedAuthority("ROLE_".concat(roleEntity.getRole().name())))
                .collect(Collectors.toList());

        return new User(userEntity.getUserName(),
                userEntity.getPasswordUser(),
                true,
                true,
                true,
                true,
                authorities
        );
    }
}
