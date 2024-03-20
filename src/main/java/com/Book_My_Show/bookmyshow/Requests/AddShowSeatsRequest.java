package com.Book_My_Show.bookmyshow.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddShowSeatsRequest {
    private Integer showId;

    private Integer priceOfClassicSeats;

    private Integer priceOfPremiumSeats;
}
