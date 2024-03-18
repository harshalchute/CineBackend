package com.Book_My_Show.bookmyshow.Controller;

import com.Book_My_Show.bookmyshow.Requests.AddTheaterRequest;
import com.Book_My_Show.bookmyshow.Requests.AddTheaterSeatRequest;
import com.Book_My_Show.bookmyshow.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("theater")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;
    @PostMapping("/addTheater")
    public String addTheater(@RequestBody AddTheaterRequest addTheaterRequest){
        String result = theaterService.addTheater(addTheaterRequest);
        return result;
    }

//    Add theater seats;
    @PostMapping("/addTheaterSeats")
    public String addTheaterSeats(@RequestBody AddTheaterSeatRequest addTheaterSeatRequest){
        String result = theaterService.addTheaterSeats(addTheaterSeatRequest);
        return result;
    }
}
