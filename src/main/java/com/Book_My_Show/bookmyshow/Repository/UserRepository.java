package com.Book_My_Show.bookmyshow.Repository;

import com.Book_My_Show.bookmyshow.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findUserByMobNo(String mobNo);
}
