package com.example.casestudy_movieproject.service.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveUserInfoRequest {
    private String id;
    @NotBlank
    @Pattern(regexp = "^[a-zA-ZÀ-Ỹà-ỹ]+( [a-zA-ZÀ-Ỹà-ỹ]+)*$",message = "Lỗi hệ thống")
    @Size(max = 50,message = "Lỗi hệ thống")
    private String name;
    @NotBlank
    private String password;
}
