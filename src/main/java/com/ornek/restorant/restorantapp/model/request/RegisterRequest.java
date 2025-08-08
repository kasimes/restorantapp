package com.ornek.restorant.restorantapp.model.request;

import com.ornek.restorant.restorantapp.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String password;
    private Role role;


}

