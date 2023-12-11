package com.example.book_my_show.Controller;


import com.example.book_my_show.RequestDTO.TheaterRequestDTO;
import com.example.book_my_show.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add-theater")
    public ResponseEntity addTheater(@RequestBody TheaterRequestDTO theeaterReuestDTO){
        try{
            String result=theaterService.addTheater(theeaterReuestDTO);
            return new ResponseEntity(result,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/revenue_from_theater")
    public ResponseEntity revenueFromTheater(@RequestParam("theaterId") Integer theaterId){
        try{
            int response=theaterService.revenueFromTheater(theaterId);
            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

//    @DeleteMapping("/remove_theater")


}
