package com.Book_My_Show.bookmyshow.Repository;

import com.Book_My_Show.bookmyshow.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Integer> {
}
