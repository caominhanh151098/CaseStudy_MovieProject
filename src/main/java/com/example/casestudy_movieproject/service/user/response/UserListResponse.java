package com.example.casestudy_movieproject.service.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserListResponse {
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String avatar;


}
