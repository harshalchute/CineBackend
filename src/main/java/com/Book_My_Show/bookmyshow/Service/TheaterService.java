package com.Book_My_Show.bookmyshow.Service;

import com.Book_My_Show.bookmyshow.Enums.TheaterSeatType;
import com.Book_My_Show.bookmyshow.Models.Theater;
import com.Book_My_Show.bookmyshow.Models.TheaterSeat;
import com.Book_My_Show.bookmyshow.Repository.TheaterRepository;
import com.Book_My_Show.bookmyshow.Repository.TheaterSeatRepository;
import com.Book_My_Show.bookmyshow.Requests.AddTheaterRequest;
import com.Book_My_Show.bookmyshow.Requests.AddTheaterSeatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterSeatRepository theaterSeatRepository;

//    public String addTheater(AddTheaterRequest addTheaterRequest){
////        Convert this addRequest into Entity
////        Using of constructor to create an object but generally constructors are not available
////        So we tackle this by creating object as below;
//        Theater theater = new Theater(); // We have this entity object
//        theater.setName(addTheaterRequest.getName());
//        theater.setAddress(addTheaterRequest.getAddress());
//        theater.setNoOfScreens(addTheaterRequest.getNoOfScreens());
//
////        Save Entity to DB;
//        theater = theaterRepository.save(theater);
//        return "The theater is with a theaterId " + theater.getTheaterId();
//    }

    //    Above is the older way to create object and do all this but we can use Builder[Annotation from Lombok Dependency] to resolve this as below;
    public String addTheater(AddTheaterRequest addTheaterRequest) {
        Theater theater = Theater.builder().address(addTheaterRequest.getAddress())
                .noOfScreens(addTheaterRequest.getNoOfScreens())
                .name(addTheaterRequest.getName())
                .build();

        //        Save Entity to DB;
        theater = theaterRepository.save(theater);
        return "The theater is with a theaterId " + theater.getTheaterId();
    }


    public String addTheaterSeats(AddTheaterSeatRequest addTheaterSeatRequest){
        int noOfClassicSeats = addTheaterSeatRequest.getNoOfClassicSeats();
        int noOfPremiumSeats = addTheaterSeatRequest.getNoOfPremiumSeats();

        Integer theaterId = addTheaterSeatRequest.getTheaterId();
        Theater theater = theaterRepository.findById(theaterId).get();

        List<TheaterSeat> theaterSeatList = new ArrayList<>();
        int classicSeatCounter = 1;
        char ch = 'A';
        int rowNo = 1;

        while(classicSeatCounter <= noOfClassicSeats){
            String seatNo = rowNo + "" + ch;
            TheaterSeat theaterSeat = TheaterSeat.builder()
                    .seatNo(seatNo)
                    .theaterSeatType(TheaterSeatType.CLASSIC)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);
//            ch = (char)(ch - 'A' + 1);
            ch++;
            if(classicSeatCounter % 5 == 0){
                rowNo = rowNo + 1;
                ch = 'A';
            }
            classicSeatCounter++;
        }



        int premiumSeatCounter = 1;
        ch = 'A';
        if(classicSeatCounter % 5 != 1){
            rowNo = rowNo + 1;
        }

        while(premiumSeatCounter <= noOfPremiumSeats){
            String seatNo = rowNo + "" + ch ;
            TheaterSeat theaterSeat = TheaterSeat.builder()
                    .seatNo(seatNo)
                    .theaterSeatType(TheaterSeatType.PREMIUM)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);
//            ch = (char)(ch - 'A' + 1);
            ch++;
            if(premiumSeatCounter % 5 == 0){
                rowNo = rowNo + 1;
                ch = 'A';
            }
            premiumSeatCounter++;
        }
        theater.setTheaterSeatList(theaterSeatList);
        theaterRepository.save(theater);
//        theaterSeatRepository.saveAll(theaterSeatList);
//        Theater seats will get automatically saved bcoz of cascading property written in the parent table

        return "Theater seats have been generated";
    }
}