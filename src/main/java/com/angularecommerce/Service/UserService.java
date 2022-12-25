package com.angularecommerce.Service;

import com.angularecommerce.Model.User;
import com.angularecommerce.Model.UsersDetails;
import com.angularecommerce.Repository.UserDetailRepository;
import com.angularecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public void registerData(@RequestBody User user){

         userRepo.save(user);
    }


    public User login(String userName, String password){
        User login= userRepo.findByUserNameAndPassword(userName,password);
        return login;


    }
}
