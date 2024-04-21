package com.Book_My_Show.bookmyshow.Requests;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class GetShowByMovieRequest {
    private LocalDate showDate;

    private LocalTime showTime;

    private String theaterName;

    private String theaterAddress;
}
