package com.Book_My_Show.bookmyshow.Repository;

import com.Book_My_Show.bookmyshow.Models.Show;
import com.Book_My_Show.bookmyshow.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Integer> {
//    public List<ShowSeat> findAllByShow(Show show); // Inbuilt method

//    Custom Query
    @Query(value = "select * from show_seats where show_show_id = :showId",nativeQuery = true)
    public List<ShowSeat> findShowSeats(Integer showId);
}
