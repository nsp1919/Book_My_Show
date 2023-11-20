package com.example.book_my_show.Service;


import com.example.book_my_show.Models.*;
import com.example.book_my_show.Repository.MovieRepository;
import com.example.book_my_show.Repository.ShowRepository;
import com.example.book_my_show.Repository.TheaterRepository;
import com.example.book_my_show.RequestDTO.AddShowRequestDTO;
import com.example.book_my_show.RequestDTO.ShowSeatRequestDTO;
import com.example.book_my_show.Transformer.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;


    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public String addShow(AddShowRequestDTO addShowRequestDTO) {
        Shows shows= ShowTransformer.convertShow(addShowRequestDTO);
        Movie movie=movieRepository.findByName(addShowRequestDTO.getMovieName());
        Theater theater=theaterRepository.findById(addShowRequestDTO.getTheaterId()).get();


        shows.setMovie(movie);
        shows.setTheater(theater);

        movie.getShowsList().add(shows);
        theater.getShowsList().add(shows);

        Shows shows1=showRepository.save(shows);
        return "Show has been added successfully with show ID "+shows1.getId();
    }

    public String enableShowSeats(ShowSeatRequestDTO showSeatRequestDTO) {
        Shows shows=showRepository.findById(showSeatRequestDTO.getShowId()).get();
        Theater theater=shows.getTheater();
        List<TheaterSeat>theaterSeatList=theater.getTheaterSeatList();
        List<ShowSeat>showSeatList=new ArrayList<>();
        for (TheaterSeat theaterSeat:theaterSeatList){
            ShowSeat showSeat= ShowSeat.builder()
                    .seatNo(theaterSeat.getSeatNo())
                    .seatType(theaterSeat.getSeatType())
                    .seatAvailable(true)
                    .shows(shows)
                    .price(0)
                    .build();

            switch (theaterSeat.getSeatType()){
                case GOLD:
                    showSeat.setPrice(showSeatRequestDTO.getPriceOfGoldSeats());
                    break;
                case SILVER:
                    showSeat.setPrice(showSeatRequestDTO.getPriceOfSilverSeats());
                    break;
                case CLASSIC:
                    showSeat.setPrice(showSeatRequestDTO.getPriceOfClassicSeats());
                    break;
                case PREMIUM:
                    showSeat.setPrice(showSeatRequestDTO.getPriceOfPremiumSeats());
                    break;
            }
            showSeatList.add(showSeat);
        }

        shows.setShowSeatList(showSeatList);
        showRepository.save(shows);

        //We can save child also yo save child use showSeatRepository.saveall(showSeatList)
        return "shows are enabled";
    }
}
