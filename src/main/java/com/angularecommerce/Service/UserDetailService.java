package com.angularecommerce.Service;

import com.angularecommerce.Model.UsersDetails;
import com.angularecommerce.Repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserDetailService {

    @Autowired
    UserDetailRepository userDetailRepository;

    public void registerData(@RequestBody UsersDetails usersDetails){

         userDetailRepository.save(usersDetails);
    }


    public UsersDetails login(String email, String password){
        UsersDetails login= userDetailRepository.findByEmailAndPassword(email,password);
        return login;


    }
}
