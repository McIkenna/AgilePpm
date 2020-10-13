package com.Iikenna.ppm.services;


import com.Iikenna.ppm.domain.User;
import com.Iikenna.ppm.exceptions.UsernameAlreadyExistException;
import com.Iikenna.ppm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser){

        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            newUser.setUsername(newUser.getUsername());
            // Make sure that password and confirm password match
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);

        }catch(Exception e){
            throw new UsernameAlreadyExistException("Username '" + newUser.getUsername() + "' already exists");
        }



    }
}
