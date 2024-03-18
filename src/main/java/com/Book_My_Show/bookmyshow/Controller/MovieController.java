package com.Book_My_Show.bookmyshow.Controller;

import com.Book_My_Show.bookmyshow.Models.Movie;
import com.Book_My_Show.bookmyshow.Service.MovieService;
import com.Book_My_Show.bookmyshow.Requests.UpdateMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody Movie movie){
        String result = movieService.addMovie(movie);
        return result;
    }

//    @PutMapping("/updateMovie")
//    public String updateMovieAttributes(@RequestParam("movieId")Integer movieId,
//                                        @RequestParam("duration")double duration,
//                                        @RequestParam("rating")double rating){
//
//    }
//    Instead of the above api call we can have api call with RequestBody with the help of DTO
//    DTO[Data Transfer Object] : It is just like encapsulation.

    @PutMapping("/updateMovie")
    public String updateMovieAttributes(@RequestBody UpdateMovieRequest movieRequest){
//        By this only relevant attributes are exposed to the client
        String result = movieService.updateMovieAttributes(movieRequest);
        return result;
    }
}
