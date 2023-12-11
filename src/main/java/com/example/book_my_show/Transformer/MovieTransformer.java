package com.example.book_my_show.Transformer;

import com.example.book_my_show.Models.Movie;
import com.example.book_my_show.RequestDTO.AddMovieRequestDTO;
import com.example.book_my_show.ResponseDTO.MovieDetailsResponse;

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
    public static MovieDetailsResponse movieDetailsResponse(Movie movie){
        MovieDetailsResponse movieDetailsResponse=MovieDetailsResponse.builder()
                .movieName(movie.getName())
                .duration(movie.getDuration())
                .rating(movie.getRating())
                .gener(movie.getGener())
                .releaseDate(movie.getReleaseDate())
                .build();

        return movieDetailsResponse;
    }
}
