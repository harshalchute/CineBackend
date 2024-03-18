package com.Book_My_Show.bookmyshow.Service;

import com.Book_My_Show.bookmyshow.Models.Movie;
import com.Book_My_Show.bookmyshow.Repository.MovieRepository;
import com.Book_My_Show.bookmyshow.Requests.UpdateMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public String addMovie(Movie movie){
        movie = movieRepository.save(movie);
        return "The movie has been saved to the DB with movieId " + movie.getMovieId();
    }


    public String updateMovieAttributes(UpdateMovieRequest movieRequest){
        Movie movie = movieRepository.findById(movieRequest.getMovieId()).get();

        double rating = movieRequest.getRating();
        double duration = movieRequest.getDuration();

        movie.setDuration(duration);
        movie.setRating(rating);

        movieRepository.save(movie);
        return "Attributes are modified";
    }
}
