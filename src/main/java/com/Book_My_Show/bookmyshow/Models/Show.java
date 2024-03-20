package com.Book_My_Show.bookmyshow.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "shows") // Don't use show bcoz it is the variable in MySQL
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showId;

    private LocalDate showDate; // YYYY-MM-DD

    private LocalTime showTime; // HH:MM:SS

    @ManyToOne
    @JoinColumn
    private Movie movie;

    @ManyToOne
    @JoinColumn
    private Theater theater;
}
