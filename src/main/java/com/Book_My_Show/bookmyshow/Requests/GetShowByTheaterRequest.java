package com.Book_My_Show.bookmyshow.Requests;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class GetShowByTheaterRequest {
    public String theaterAddress;

    public String movieName;

    public LocalTime showTime;

    public LocalDate showDate;
}
