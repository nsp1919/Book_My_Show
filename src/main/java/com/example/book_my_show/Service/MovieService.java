package com.example.book_my_show.Service;


import com.example.book_my_show.Models.Movie;
import com.example.book_my_show.Repository.MovieRepository;
import com.example.book_my_show.RequestDTO.AddMovieRequestDTO;
import com.example.book_my_show.Transformer.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;


    public String addMovie(AddMovieRequestDTO addMovieRequestDTO) {

        Movie movie= MovieTransformer.convertMovie(addMovieRequestDTO);

        movieRepository.save(movie);

        return "Movie added";

    }
}
