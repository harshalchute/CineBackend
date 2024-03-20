package com.Book_My_Show.bookmyshow.Controller;

import com.Book_My_Show.bookmyshow.Models.User;
import com.Book_My_Show.bookmyshow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/address")
    public String addUser(@RequestBody User user){
        String result = userService.addUser(user);
        return result;
    }
}
