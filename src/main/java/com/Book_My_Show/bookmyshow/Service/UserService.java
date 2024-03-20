package com.Book_My_Show.bookmyshow.Service;

import com.Book_My_Show.bookmyshow.Models.User;
import com.Book_My_Show.bookmyshow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public String addUser(User user){
        user = userRepository.save(user);
        return "The user has been saved to the DB with userId + " + user.getUserId() + " name " + user.getName();
    }
}
