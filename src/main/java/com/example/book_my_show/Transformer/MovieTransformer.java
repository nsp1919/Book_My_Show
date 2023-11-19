package com.example.book_my_show.Transformer;

import com.example.book_my_show.Models.Movie;
import com.example.book_my_show.RequestDTO.AddMovieRequestDTO;

public class MovieTransformer {

    public static Movie convertMovie(AddMovieRequestDTO addMovieRequestDTO){
        Movie movie=Movie.builder()
                .gener(addMovieRequestDTO.getGener())
                .name(addMovieRequestDTO.getName())
                .duration(addMovieRequestDTO.getDuration())
                .rating(addMovieRequestDTO.getRating())
                .releaseDate(addMovieRequestDTO.getReleaseDate())
                .build();

        return movie;
    }
}
