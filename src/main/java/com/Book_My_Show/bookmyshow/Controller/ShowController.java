package com.Book_My_Show.bookmyshow.Controller;


import com.Book_My_Show.bookmyshow.Models.Show;
import com.Book_My_Show.bookmyshow.Models.Ticket;
import com.Book_My_Show.bookmyshow.Requests.AddShowRequest;
import com.Book_My_Show.bookmyshow.Requests.AddShowSeatsRequest;
import com.Book_My_Show.bookmyshow.Requests.GetShowByMovieRequest;
import com.Book_My_Show.bookmyshow.Requests.GetShowByTheaterRequest;
import com.Book_My_Show.bookmyshow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PostMapping("/addShowSeats")
    public String addShowSeats(@RequestBody AddShowSeatsRequest showSeatsRequest){
        String result = showService.addShowSeats(showSeatsRequest);
        return result;
    }


    @GetMapping("/getShowByMovie")
    public ResponseEntity<List<GetShowByMovieRequest>> getShowByMovie(@RequestParam String movieName){
        try{
            List<GetShowByMovieRequest> list = showService.getShowByMovie(movieName);
            return new ResponseEntity(list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getShowByTheater")
    public ResponseEntity<List<GetShowByTheaterRequest>> getShowByTheater(@RequestParam String theaterName){
        try{
            List<GetShowByTheaterRequest> list = showService.getShowByTheater(theaterName);
            return new ResponseEntity(list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
