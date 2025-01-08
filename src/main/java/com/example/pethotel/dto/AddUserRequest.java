package com.example.pethotel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String username;
    private String userid;
    private String password;
    private String nickname;
    private String userphone;
    private String userrole;
}
