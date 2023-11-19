package com.example.book_my_show.Controller;


import com.example.book_my_show.RequestDTO.BookTicketRequestDTO;
import com.example.book_my_show.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book-ticket")
    public ResponseEntity bookTicket(@RequestBody BookTicketRequestDTO bookTicketRequestDTO){
        String result=ticketService.bookTicket(bookTicketRequestDTO);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
