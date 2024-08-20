package com.library.management.system.user;
import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;


@Entity
@Setter
@Getter
@Builder//@No-arg contractor does not match together
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(generator ="IdGen" ,strategy = GenerationType.SEQUENCE)
    @Column(name ="user_id")
    private Long id;

    @Column(name ="user_name")
    private String name;

    @Column(name ="user_email")
    private String email;

    @Column(name ="user_password")
    private String password;


}
