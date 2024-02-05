package com.Boppo_Task.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
     private String  city;

     private String email;


     private String username;

     private String password;


     private String confirmpassword;


    public User(String name, String city, String email, String username, String password, String confirmpassword) {
        this.name= name;
        this.city=city;
        this.email=email;
        this.username=username;
        this.password=password;
        this.confirmpassword=confirmpassword;
    }
}
