package com.angularecommerce.Repository;

import com.angularecommerce.Model.UsersDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public  interface  UserDetailRepository extends JpaRepository<UsersDetails, Integer> {
    UsersDetails findByEmailAndPassword(String email, String password);
}
