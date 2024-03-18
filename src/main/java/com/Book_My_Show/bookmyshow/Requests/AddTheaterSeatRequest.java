package com.Book_My_Show.bookmyshow.Requests;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter

public class AddTheaterSeatRequest {
    private int theaterId;

    private int noOfClassicSeats;

    private int noOfPremiumSeats;

}
