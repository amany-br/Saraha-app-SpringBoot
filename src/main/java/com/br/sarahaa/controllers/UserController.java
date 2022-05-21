package com.br.sarahaa.controllers;

import java.util.List;

import com.br.sarahaa.dto.AuthenticationRequest;
import com.br.sarahaa.dto.AuthenticationResponse;
import com.br.sarahaa.dto.UserDto;
import com.br.sarahaa.services.UserService;
import com.br.sarahaa.services.auth.AppUserDetailsService;
import com.br.sarahaa.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<UserDto> save(@RequestBody UserDto user) {
        return ResponseEntity.ok(service.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        // this code snippet does all the authentication process
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String jwt = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(
                AuthenticationResponse.builder()
                        .token(jwt)
                        .build()
        );
    }

    @GetMapping("/{user-email}")
    public ResponseEntity<UserDto> findByEmail(
            @PathVariable("user-email") String email
    ) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @GetMapping("/recently-joined-users")
    public ResponseEntity<List<UserDto>> recentlyJoinedUsers() {
        return ResponseEntity.ok(service.recentlyJoinedUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUser(
            @RequestParam(value = "first-name", required = false) String firstName,
            @RequestParam(value = "last-name", required = false) String lastName,
            @RequestParam(value = "user-name", required = false) String userName
    ) {
        return ResponseEntity.ok(service.searchUser(firstName, lastName, userName));
    }

}

