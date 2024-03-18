package com.Book_My_Show.bookmyshow.Repository;

import com.Book_My_Show.bookmyshow.Models.TheaterSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeat,Integer> {
}
