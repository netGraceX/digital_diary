package com.netgrace.digital_diary.security.auth;

import lombok.Data;

@Data
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
