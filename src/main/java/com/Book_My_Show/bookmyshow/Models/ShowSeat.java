package com.Book_My_Show.bookmyshow.Models;

import com.Book_My_Show.bookmyshow.Enums.TheaterSeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "show_seats")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showSeatId;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private TheaterSeatType theaterSeatType;

    private Integer price;

    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn
    private Show show;
}
