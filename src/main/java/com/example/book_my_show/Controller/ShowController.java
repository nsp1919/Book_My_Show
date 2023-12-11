package com.example.book_my_show.Controller;


import com.example.book_my_show.Models.Shows;
import com.example.book_my_show.RequestDTO.AddShowRequestDTO;
import com.example.book_my_show.RequestDTO.ShowSeatRequestDTO;
import com.example.book_my_show.ResponseDTO.ShowDetailsResponse;
import com.example.book_my_show.ResponseDTO.ShowResponseForMovie;
import com.example.book_my_show.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("shows")
public class ShowController {

    @Autowired
    ShowService showService;


    @PostMapping("/add-show")
    public ResponseEntity addShow(@RequestBody AddShowRequestDTO addShowRequestDTO){
        try{
            String result=showService.addShow(addShowRequestDTO);
            return new ResponseEntity(result,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/enable-show-seat")
    public ResponseEntity enableShowSeats(@RequestBody ShowSeatRequestDTO showSeatRequestDTO){
        try{
            String response=showService.enableShowSeats(showSeatRequestDTO);
            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/most-recomended-movie")
    public ResponseEntity mostShowedMovie(@RequestParam("date")LocalDate reqDate){
        try{
            String response=showService.mostShowedMovie(reqDate);;
            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-available-seats")
    public ResponseEntity getAvailableSeatsForShow(@RequestParam("showId") Integer showId){
        try{
            List<String> response=showService.getAvailableSeatsForShow(showId);;
            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get_show_details")
    public ResponseEntity getShowDetails(@RequestParam("showId") Integer showId){
        try{
            ShowDetailsResponse response=showService.getShowDetails(showId);
            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/get_shows_on_given_date")
    public ResponseEntity getShowsOnGivenDate(@RequestParam("movieId") Integer mpvieId,@RequestParam("date") LocalDate date){
        try{
            List<ShowResponseForMovie> response=showService.getShowsOnGivenDate(mpvieId,date);
            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
