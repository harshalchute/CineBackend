package com.Book_My_Show.bookmyshow.Models;

import com.Book_My_Show.bookmyshow.Enums.TheaterSeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "theater_seats")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheaterSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterSeatId;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private TheaterSeatType theaterSeatType;

    @JoinColumn
    @ManyToOne
    private Theater theater;
}
