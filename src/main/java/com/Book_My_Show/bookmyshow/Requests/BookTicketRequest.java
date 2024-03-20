package com.Book_My_Show.bookmyshow.Requests;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Data
public class BookTicketRequest {
    private String movieName;

    private LocalDate showDate;

    private LocalTime showTime;

    private List<String> requestedSeats;

    private Integer theaterId;

    private String mobNo;
}
