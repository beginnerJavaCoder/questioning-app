package com.example.controller;

import com.example.entity.user.User;
import com.example.form.AuthenticationForm;
import com.example.security.jwt.JwtTokenUtil;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager,
                                        JwtTokenUtil jwtTokenUtil,
                                        UserDetailsService userDetailsService,
                                        UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationForm authenticationForm) {
        try {
            String username = authenticationForm.getUsername();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, authenticationForm.getPassword()));

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            String token = jwtTokenUtil.generateToken(userDetails);

            User user = userService.findByUsername(username);
            Map<String, String> response = new HashMap<>();

            response.put("token", token);
            response.put("username", userDetails.getUsername());
            response.put("userId", String.valueOf(user.getId()));

            return ResponseEntity.ok(response);

        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
