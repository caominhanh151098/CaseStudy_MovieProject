package com.example.casestudy_movieproject.service.request;

import com.example.casestudy_movieproject.model.enums.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSaveRequest {

    private String username;

    private String password;

    private String email;

    private String role;

    private String avatar;

    private String name;
}
