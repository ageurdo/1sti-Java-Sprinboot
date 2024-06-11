package com.ageurdo.demo_user_auth_api.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPasswordDto {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}