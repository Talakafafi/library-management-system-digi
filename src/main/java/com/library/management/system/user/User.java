package com.library.management.system.user;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@Builder
public class User {
    private String name;
    private String email;
    private String password;
}
