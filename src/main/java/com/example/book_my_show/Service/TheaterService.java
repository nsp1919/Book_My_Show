package com.example.book_my_show.Service;

import com.example.book_my_show.Enums.SeatType;
import com.example.book_my_show.Models.Theater;
import com.example.book_my_show.Models.TheaterSeat;
import com.example.book_my_show.Repository.TheaterRepository;
import com.example.book_my_show.Repository.TheaterSeatRepository;
import com.example.book_my_show.RequestDTO.TheaterRequestDTO;
import com.example.book_my_show.Transformer.TheaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    public String addTheater(TheaterRequestDTO theeaterReuestDTO) {

        Theater theater= TheaterTransformer.convertAddTheater(theeaterReuestDTO);

        addTheaterSeats(theater,theeaterReuestDTO);

        theaterRepository.save(theater);

        return "Theater added";

    }
    public void addTheaterSeats(Theater theater,TheaterRequestDTO theaterRequestDTO){
        int classicSeatsCount=theaterRequestDTO.getNumOfClassicSeats();
        int premiumSeatsCount=theaterRequestDTO.getNoOfPremiumSeats();
        int goldSeatsCount=theaterRequestDTO.getNoOfGoldSeats();
        int silverSeatsCount=theaterRequestDTO.getNoOfSilverSeats();
        int seatsPerRow=theaterRequestDTO.getNoOfSeatsInEachRow();

        List<TheaterSeat> theaterSeatList=new ArrayList<>();
        int num=1;
        char row='@';
        for (int i=1;i<=classicSeatsCount;i++){
            if (i%seatsPerRow==1){
                row++;
                num=1;
            }
            String seatNo=row+""+num;
            num++;

            TheaterSeat theaterSeat=TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.CLASSIC)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);

        }
        num=1;
        for (int i=1;i<=premiumSeatsCount;i++){
            if (i%seatsPerRow==1){
                row++;
                num=1;
            }
            String seatNo=row+""+num;
            num++;
            TheaterSeat theaterSeat=TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.PREMIUM)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);

        }
        num=1;

        for (int i=1;i<=goldSeatsCount;i++){
            if (i%seatsPerRow==1){
                row++;
                num=1;
            }
            String seatNo=row+""+num;
            num++;

            TheaterSeat theaterSeat=TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.GOLD)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);

        }
        num=1;
        for (int i=1;i<=silverSeatsCount;i++){
            if (i%seatsPerRow==1){
                row++;
                num=1;
            }
            String seatNo=row+""+num;
            num++;

            TheaterSeat theaterSeat=TheaterSeat.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.SILVER)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);

        }
        theater.setTheaterSeatList(theaterSeatList);



    }
}
