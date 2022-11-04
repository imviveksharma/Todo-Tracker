package com.niit.UserAuthentication.domain;


import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class User {

    @Id
    private String email;
    private String password;




}
