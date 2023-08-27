package com.example.questApp.controller;

import com.example.questApp.entity.User;
import com.example.questApp.requests.UserRequest;
import com.example.questApp.security.JwtTokenProvider;
import com.example.questApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login (@RequestBody UserRequest loginRequest)
    {
        UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);//  By providing an authToken, we enable it to return an authentication object to us.
        SecurityContextHolder.getContext().setAuthentication(auth);// auth objesi contexte yerleştiriliyor
        String jwtToken =jwtTokenProvider.generateJwtToken(auth);
        return "Bearer "+jwtToken;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest registerRequest)
    {
        if(userService.getOneUSerByName(registerRequest.getUsername())!=null)
        {
            return new ResponseEntity<>("UserName already in use ", HttpStatus.BAD_REQUEST);
        }
        User user=new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userService.saveUser(user);
        return new ResponseEntity<>("User Created",HttpStatus.OK);

    }
}
