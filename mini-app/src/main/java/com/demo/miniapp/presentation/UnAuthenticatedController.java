package com.demo.miniapp.presentation;

import com.demo.miniapp.authentication.CustomUser;
import com.demo.miniapp.authentication.JwtUtil;
import com.demo.miniapp.authentication.UserDetailsServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@Slf4j
@RequiredArgsConstructor
@RestController

public class UnAuthenticatedController {
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ApiResponse<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final CustomUser userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new ApiResponse(HttpStatus.OK, new AuthenticationResponse(jwt));
    }

}

@Getter
@Setter
class AuthenticationRequest implements Serializable {

    private static final long serialVersionUID = 2873170445981799796L;
    private String username;
    private String password;
}

@Getter
@Setter
class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 8347629198163105182L;
    private String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}