package com.example.book_my_show.Transformer;

import com.example.book_my_show.Models.Movie;
import com.example.book_my_show.Models.Theater;
import com.example.book_my_show.RequestDTO.TheaterRequestDTO;
import com.example.book_my_show.ResponseDTO.MovieDetailsResponse;
import com.example.book_my_show.ResponseDTO.TheaterDetailsResponse;

public class TheaterTransformer {

    public static Theater convertAddTheater(TheaterRequestDTO theaterRequestDTO){

        Theater theater=Theater.builder().name(theaterRequestDTO.getName())
                .address(theaterRequestDTO.getAddress())
                .city(theaterRequestDTO.getCity()).build();

        return theater;
    }
    public static TheaterDetailsResponse theaterDetailsResponse(Theater theater) {
        TheaterDetailsResponse theaterDetailsResponse = TheaterDetailsResponse.builder()
                .theaterName(theater.getName())
                .theaterAddress(theater.getAddress())
                .city(theater.getCity())
                .build();

        return theaterDetailsResponse;
    }
}
