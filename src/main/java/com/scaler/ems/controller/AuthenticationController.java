package com.scaler.ems.controller;

import com.scaler.ems.model.AuthRequest;
import com.scaler.ems.model.UserBo;
import com.scaler.ems.service.UserDetailServiceImpl;
import com.scaler.ems.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailServiceImpl userDetailService;


    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(summary = "Sign up user")
    @ApiResponse(responseCode = "201", description = "Signup successful", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserBo.class))
    })
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserBo userBo) {
        userDetailService.save(userBo);
    }

    @PostMapping("/login")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
