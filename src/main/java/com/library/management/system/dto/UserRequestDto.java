package com.library.management.system.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
}
