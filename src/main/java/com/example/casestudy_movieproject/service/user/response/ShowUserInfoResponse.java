package com.example.casestudy_movieproject.service.user.response;

import com.example.casestudy_movieproject.model.enums.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowUserInfoResponse {
    private String id;
    private String username;
    private String email;
    private String avatar;
    private String name;
}
