//package com.demo.miniapp.authentication;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.demo.miniapp.dto.UserDTO;
//import com.demo.miniapp.repository.UserRepository;
//import com.demo.miniapp.repository.entity.UserEntity;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.*;
//
//@Slf4j
//public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final AuthenticationManager authenticationManager;
//    private final AuthPropConfig authPropConfig;
//    private final UserRepository userRepository;
//
//    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
//                                   AuthPropConfig authPropConfig, UserRepository userRepository) {
//        this.authenticationManager = authenticationManager;
//        this.authPropConfig = authPropConfig;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            UserDTO credentials = new ObjectMapper()
//                    .readValue(request.getInputStream(), UserDTO.class);
//            UserEntity userEntity = userRepository.findByUsername(credentials.getUsername());
//            if(Objects.nonNull(userEntity)) {
//                log.info("user credential: {}", userEntity);
//                return authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(
//                                credentials.getUsername(),
//                                credentials.getPassword(),
//                                Arrays.asList(new SimpleGrantedAuthority(userEntity.getRole())))
//                );
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            FilterChain chain,
//                                            Authentication auth) throws IOException, ServletException {
//        String token = JWT.create()
//                .withSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + authPropConfig.getExpirationTime()))
//                .withArrayClaim("activities", new String[] {"read", "write"})
//                .sign(Algorithm.HMAC512(authPropConfig.getSecret().getBytes()));
//        log.info("JWT: {}", token);
//        response.addHeader(authPropConfig.getAuthorizationHeader(), authPropConfig.getTokenPrefix() + token);
//    }
//}
