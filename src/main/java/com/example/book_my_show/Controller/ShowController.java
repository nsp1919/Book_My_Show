package com.example.book_my_show.Controller;


import com.example.book_my_show.Models.Shows;
import com.example.book_my_show.Repository.ShowSeatRepository;
import com.example.book_my_show.RequestDTO.AddShowRequestDTO;
import com.example.book_my_show.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shows")
public class ShowController {

    @Autowired
    ShowService showService;

    @Autowired
    ShowSeatRepository showSeatRepository;

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
    public String enableShowSeats(@RequestParam("showId") Integer showId){

        return showService.enableShowSeats(showId);
    }
}
