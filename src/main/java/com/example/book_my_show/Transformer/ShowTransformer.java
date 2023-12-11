package com.example.book_my_show.Transformer;

import com.example.book_my_show.Models.Shows;
import com.example.book_my_show.RequestDTO.AddShowRequestDTO;
import com.example.book_my_show.ResponseDTO.ShowDetailsResponse;
import com.example.book_my_show.ResponseDTO.ShowResponseForMovie;

public class ShowTransformer {

    public static Shows convertShow(AddShowRequestDTO addShowRequestDTO){

        Shows shows=Shows.builder()
                .showTime(addShowRequestDTO.getShowTime())
                .showDate(addShowRequestDTO.getShowDate())
                .build();

        return shows;

    }

    public static ShowDetailsResponse showDetailsResponse(Shows shows){
        ShowDetailsResponse showDetailsResponse=ShowDetailsResponse.builder()
                .showDate(shows.getShowDate())
                .showTime(shows.getShowTime())
                .movieDetailsResponse(MovieTransformer.movieDetailsResponse(shows.getMovie()))
                .theaterDetailsResponse(TheaterTransformer.theaterDetailsResponse(shows.getTheater()))
                .build();
        return showDetailsResponse;
    }

    public static ShowResponseForMovie showResponseForMovie(Shows shows){
        ShowResponseForMovie showDetailsResponse=ShowResponseForMovie.builder()
                .showId(shows.getId())
                .time(shows.getShowTime())
                .theaterDetailsResponse(TheaterTransformer.theaterDetailsResponse(shows.getTheater()))
                .build();
        return showDetailsResponse;
    }

}
