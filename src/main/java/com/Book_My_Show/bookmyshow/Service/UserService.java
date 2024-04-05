package com.Book_My_Show.bookmyshow.Service;

import com.Book_My_Show.bookmyshow.Models.User;
import com.Book_My_Show.bookmyshow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public String addUser(User user){
        user = userRepository.save(user);


        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Welcome to your Movie Booking Application");
        message.setFrom("harshaldummy67@gmail.com");
        message.setTo(user.getEmailId());
        String body = "Hi " + user.getName() + "!!" + "\n" +
                "Welcome to your Movie Booking Application !! , Feel free " +
                "to browse the movies and use COUPON START100 for instant discount";
        message.setText(body);


        return "The user has been saved to the DB with userId  " + user.getUserId() + " name " + user.getName();
    }
}
