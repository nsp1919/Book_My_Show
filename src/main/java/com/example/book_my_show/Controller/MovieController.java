package com.example.book_my_show.Controller;


import com.example.book_my_show.RequestDTO.AddMovieRequestDTO;
import com.example.book_my_show.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody AddMovieRequestDTO addMovieRequestDTO){
        try{
            String result=movieService.addMovie(addMovieRequestDTO);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
