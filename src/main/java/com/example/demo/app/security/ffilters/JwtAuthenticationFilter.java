package com.example.demo.app.security.ffilters;

import com.example.demo.app.models.UserEntity;
import com.example.demo.app.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        UserEntity userEntity = null;
        String userName = null;
        String password = null;
        //recuperamos el usuario y contraseña
        try {
            userEntity = new ObjectMapper()
                    .readValue(request.getInputStream(), UserEntity.class);
            userName = userEntity.getUserName();
            password = userEntity.getPasswordUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, password);
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {

        //obtenemos el objeto que contiene todos los detalles del usuario
        User user = (User) authResult.getPrincipal();
        //generamos el token de acceso
        String token = jwtUtils.generateToken(user.getUsername());
        response.addHeader("Authorization", token);
        Map<String, String> httpReponse = new HashMap<>();
        httpReponse.put("token", token);
        httpReponse.put("message", "Authentication Ok");
        httpReponse.put("username", user.getUsername());
        //escribimos el mapa a json
        response.getWriter().write(new ObjectMapper().writeValueAsString(httpReponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
