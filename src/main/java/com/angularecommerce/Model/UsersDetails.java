package com.angularecommerce.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String phone;

    private String password;
}
