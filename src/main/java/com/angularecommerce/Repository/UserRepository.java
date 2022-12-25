package com.angularecommerce.Repository;



import com.angularecommerce.Model.User;
import com.angularecommerce.Model.UsersDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<com.angularecommerce.Model.User,Integer> {
    User findByUserName(String userName);
    User findByUserNameAndPassword(String userName, String password);
}