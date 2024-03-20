package com.Book_My_Show.bookmyshow.Repository;

import com.Book_My_Show.bookmyshow.Models.Movie;
import com.Book_My_Show.bookmyshow.Models.Show;
import com.Book_My_Show.bookmyshow.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {

//    @Query(value = "select * from shows where show_date = :showDate
//                                              show_time = :showTime
//                                              movie = :movie
//                                              theater = :theater" , nativeQuery = true)
//    Show findShow(LocalDate showDate, LocalTime showTime, Movie movie, Theater theater);


//    Write custom query for the below;
    public Show findShowByShowDateAndShowTimeAndMovieAndTheater(LocalDate showDate,
                                                                LocalTime showTime,
                                                                Movie movie,
                                                                Theater theater);
}
