package com.yube.model.dto.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    private String accessToken;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse() {
    }

    public JwtResponse(String accessToken, String id, String username, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}