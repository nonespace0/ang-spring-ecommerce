package com.angularecommerce.Repository;



import com.angularecommerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<com.angularecommerce.Model.User,Integer> {
    User findByUserName(String userName);
}