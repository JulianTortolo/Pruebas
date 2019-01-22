package org.apache.maven.services;

import org.apache.maven.domain.Tasks.Users.User;
import org.apache.maven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void CreateUser(User user){
        try{
            userRepository.save(user);
        }
        catch (Exception e){

        }
    }
}