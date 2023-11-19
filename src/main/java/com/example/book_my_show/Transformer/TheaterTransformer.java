package com.example.book_my_show.Transformer;

import com.example.book_my_show.Models.Theater;
import com.example.book_my_show.RequestDTO.TheaterRequestDTO;

public class TheaterTransformer {

    public static Theater convertAddTheater(TheaterRequestDTO theaterRequestDTO){

        Theater theater=Theater.builder().name(theaterRequestDTO.getName())
                .address(theaterRequestDTO.getAddress())
                .city(theaterRequestDTO.getCity()).build();

        return theater;
    }


}
