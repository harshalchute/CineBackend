package com.Book_My_Show.bookmyshow.Controller;


import com.Book_My_Show.bookmyshow.Requests.AddShowRequest;
import com.Book_My_Show.bookmyshow.Requests.AddShowSeatsRequest;
import com.Book_My_Show.bookmyshow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shows")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/addShow")
    public String addShows(@RequestBody AddShowRequest showRequest){
        String result = showService.addShows(showRequest);
        return result;
    }


    @PostMapping("addShowSeats")
    public String addShowSeats(@RequestBody AddShowSeatsRequest showSeatsRequest){
        String result = showService.addShowSeats(showSeatsRequest);
        return result;
    }
}
