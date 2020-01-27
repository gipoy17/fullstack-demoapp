//package com.demo.miniapp.authentication;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//
//@Slf4j
//public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
//    private final AuthPropConfig authPropConfig;
//    public JWTAuthorizationFilter(AuthenticationManager authManager,
//                                  AuthPropConfig authPropConfig) {
//        super(authManager);
//        this.authPropConfig = authPropConfig;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain chain) throws IOException, ServletException {
//        String header = request.getHeader(authPropConfig.getAuthorizationHeader());
//        if (header == null || !header.startsWith(authPropConfig.getTokenPrefix())) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        chain.doFilter(request, response);
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
//        String token = request.getHeader(authPropConfig.getAuthorizationHeader());
//        log.info("token: {}", token);
//        if (token != null) {
//            // parse the token.
//            String user = JWT.require(Algorithm.HMAC512(authPropConfig.getSecret().getBytes()))
//                    .build()
//                    .verify(token.replace(authPropConfig.getTokenPrefix(), ""))
//                    .getSubject();
//            log.info("user: {}", user);
//            if (user != null) {
//                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
//            }
//            return null;
//        }
//        return null;
//    }
//}
