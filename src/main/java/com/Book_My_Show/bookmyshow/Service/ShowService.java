package com.Book_My_Show.bookmyshow.Service;

import com.Book_My_Show.bookmyshow.Enums.TheaterSeatType;
import com.Book_My_Show.bookmyshow.Models.*;
import com.Book_My_Show.bookmyshow.Repository.MovieRepository;
import com.Book_My_Show.bookmyshow.Repository.ShowRepository;
import com.Book_My_Show.bookmyshow.Repository.ShowSeatRepository;
import com.Book_My_Show.bookmyshow.Repository.TheaterRepository;
import com.Book_My_Show.bookmyshow.Requests.AddShowRequest;
import com.Book_My_Show.bookmyshow.Requests.AddShowSeatsRequest;
import com.Book_My_Show.bookmyshow.Requests.GetShowByMovieRequest;
import com.Book_My_Show.bookmyshow.Requests.GetShowByTheaterRequest;
import jdk.jshell.spi.ExecutionControlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    public String addShows(AddShowRequest showRequest){
//        Build an object of the show entity and save it to DB

//        I need to get the Movie Entity and Theater Entity to create the Show Entity
        Movie movie = movieRepository.findMovie(showRequest.getMovieName());

        Theater theater = theaterRepository.findById(showRequest.getTheaterId()).get();

        Show show = Show.builder()
                .showDate(showRequest.getShowDate())
                .showTime(showRequest.getShowTime())
                .movie(movie)
                .theater(theater)
                .build();

        show = showRepository.save(show);

        return "The show has been saved to the DB with showId " + show;
    }



    public String addShowSeats(AddShowSeatsRequest showSeatsRequest){
        Integer showId = showSeatsRequest.getShowId();
        Show show = showRepository.findById(showId).get();

        Theater theater = show.getTheater();
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = new ArrayList<>();

//        Now task is to create show seat list
        for(TheaterSeat theaterSeat : theaterSeatList){
            ShowSeat showSeat = ShowSeat.builder()
                    .seatNo(theaterSeat.getSeatNo())
                    .theaterSeatType(theaterSeat.getTheaterSeatType())
                    .isAvailable(Boolean.TRUE)
                    .show(show)
                    .build();

            if(theaterSeat.getTheaterSeatType().equals(TheaterSeatType.CLASSIC)){
                showSeat.setPrice(showSeatsRequest.getPriceOfClassicSeats());
            }else{
                showSeat.setPrice(showSeatsRequest.getPriceOfPremiumSeats());
            }

            showSeatList.add(showSeat);
        }

        showSeatRepository.saveAll(showSeatList);
        return "Show seats have been generated for the given showId " + showId;
    }

    public List<GetShowByMovieRequest> getShowByMovie(String movieName) throws Exception{
        boolean flag = false;
        List<Show> list = showRepository.findAll();
        List<GetShowByMovieRequest> ansList = new ArrayList<>();
        for(Show show : list) {
            if ((show.getMovie() != null) && (show.getMovie().getMovieName().equals(movieName))) {

                flag = true;

                Theater theater = show.getTheater();

                GetShowByMovieRequest showDto = new GetShowByMovieRequest();

                showDto.setShowDate(show.getShowDate());
                showDto.setShowTime(show.getShowTime());
                showDto.setTheaterName(theater.getName());
                showDto.setTheaterAddress(theater.getAddress());

                ansList.add(showDto);
            }
        }
        if(!flag){
            throw new Exception("Shows are not available for this Movie : " + movieName);
        }
        return ansList;
    }


    public List<GetShowByTheaterRequest> getShowByTheater(String theaterName) throws Exception{
        boolean flag = false;
        List<Show> listShow = showRepository.findAll();
        List<GetShowByTheaterRequest> ansList = new ArrayList<>();
        for(Show show : listShow){
            Theater theater = show.getTheater();
            if((show.getMovie() != null) && (show.getTheater() != null) && (theater.getName().equals(theaterName))){
                flag = true;

                Movie movie = show.getMovie();

                GetShowByTheaterRequest showDto = new GetShowByTheaterRequest();

                showDto.setMovieName(movie.getMovieName());
                showDto.setTheaterAddress(theater.getAddress());
                showDto.setShowTime(show.getShowTime());
                showDto.setShowDate(show.getShowDate());

                ansList.add(showDto);
            }
        }
        if(!flag){
            throw new Exception("Shows are not available for the Theater : " + theaterName);
        }
        return ansList;
    }
}