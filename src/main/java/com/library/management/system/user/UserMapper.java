package com.library.management.system.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User toModel(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return User.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();

    }


    public static UserEntity toEntity(User model) {
        if (model == null) {
            return null;
        }

        return UserEntity.builder()
                .name(model.getName())
                .email(model.getEmail())
                .password(model.getPassword())
                .build();
    }
}
