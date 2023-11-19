package com.example.book_my_show.Service;


import com.example.book_my_show.Models.*;
import com.example.book_my_show.Repository.*;
import com.example.book_my_show.RequestDTO.BookTicketRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

//    @Autowired
//    TheaterRepository theaterRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;


    @Autowired
    TicketRepository ticketRepository;


    public String bookTicket(BookTicketRequestDTO bookTicketRequestDTO) {
        List<User> userList=userRepository.findByMobileNo(bookTicketRequestDTO.getMobileNo());
        User user=userList.get(0);
        Movie movie=movieRepository.findByName(bookTicketRequestDTO.getMovieName());
//        Theater theater=theaterRepository.findById(bookTicketRequestDTO.getTheaterId()).get();
        Ticket ticket=new Ticket();
        ticket.setSeatNo(bookTicketRequestDTO.getSeatNo());
        ShowSeat showSeat=showSeatRepository.findBySeatNo(bookTicketRequestDTO.getSeatNo());
        Shows show=showRepository.findById(bookTicketRequestDTO.getShowId()).get();
        ticket.setPrice(122);
        ticket.setUser(user);
        showSeat.setSeatAvailable(false);
        showRepository.save(show);
        ticketRepository.save(ticket);
        user.getTicketList().add(ticket);





        String temp="Ticket booked "+ticket.getBookingId()+" for the movie "+movie.getName()+" show id "+show.getId();

        return temp;
    }
}
