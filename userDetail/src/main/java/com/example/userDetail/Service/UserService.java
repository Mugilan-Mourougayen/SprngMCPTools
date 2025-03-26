package com.example.userDetail.Service;


import com.example.userDetail.Modal.User;
import com.example.userDetail.Repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


@Tool
    public User findOneByEmail(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        return user.orElse(null);
    }


    @Tool
    public User findOneByName(String name) {
        Optional<User> user = userRepo.findByName(name);
        return user.orElse(null);
    }

}
