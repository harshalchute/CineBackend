package com.Book_My_Show.bookmyshow.Service;

import com.Book_My_Show.bookmyshow.Exceptions.SeatUnavailableException;
import com.Book_My_Show.bookmyshow.Models.*;
import com.Book_My_Show.bookmyshow.Repository.*;
import com.Book_My_Show.bookmyshow.Requests.BookTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket bookTicket(BookTicketRequest bookTicketRequest) throws Exception{
//        1. Calculate the total cost of the tickets

        Movie movie = movieRepository.findMovie(bookTicketRequest.getMovieName());
        Theater theater = theaterRepository.findById(bookTicketRequest.getTheaterId()).get();

//        1.1 find showEntity with date and time
        Show show = showRepository.findShowByShowDateAndShowTimeAndMovieAndTheater(bookTicketRequest.getShowDate(),bookTicketRequest.getShowTime(),movie,theater);

        Integer showId = show.getShowId();
        List<ShowSeat> showSeatList = showSeatRepository.findShowSeats(showId);

//        1.2 Calculate the total Amt and if all seats mentioned are available or not
        int totalAmount = 0;
        Boolean areAllSeatsAvailable = Boolean.TRUE;

        for(String seatNo : bookTicketRequest.getRequestedSeats()){
            for(ShowSeat showSeat : showSeatList){
                if(showSeat.getSeatNo().equals(seatNo)){
                    if(showSeat.getIsAvailable() == Boolean.FALSE){
                        areAllSeatsAvailable = Boolean.FALSE;
                        break;
                    }
                    totalAmount = totalAmount + showSeat.getPrice();
                }
            }
        }
        if(areAllSeatsAvailable == Boolean.FALSE){
            throw new SeatUnavailableException("The requested seats are unavailable");
        }

//        2. Make the seats booked : (only if seats are available : otherwise throw exception)
        for(String seatNo : bookTicketRequest.getRequestedSeats()) {
            for (ShowSeat showSeat : showSeatList) {
                if (showSeat.getSeatNo().equals(seatNo)) {
                    showSeat.setIsAvailable(Boolean.FALSE);
                }
            }
        }

        User user = userRepository.findUserByMobNo(bookTicketRequest.getMobNo());

//        3. Save the ticketEntity
        Ticket ticket = Ticket.builder().user(user)
                .movieName(bookTicketRequest.getMovieName())
                .showDate(bookTicketRequest.getShowDate())
                .showTime(bookTicketRequest.getShowTime())
                .theaterNameAndAddress(theater.getName() + " " + theater.getAddress())
                .totalAmtPaid(totalAmount)
                .build();

//        4. Generate and return back the actual ticket response

        ticket = ticketRepository.save(ticket);
        return ticket;
    }
}
