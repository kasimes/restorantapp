package com.ornek.restorant.restorantapp.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {

    private String access_token;

    private String token_type="Bearer";

    public JwtAuthenticationResponse(String access_token) {
        this.access_token = access_token;

    }
}
