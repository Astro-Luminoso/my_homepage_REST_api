package me.h_yang.my_homepage.service;

import me.h_yang.my_homepage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkUser(String email) {

        return userRepository.findByEmail(email) != null;
    }


}
