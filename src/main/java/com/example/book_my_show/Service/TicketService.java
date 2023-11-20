package com.example.book_my_show.Service;


import com.example.book_my_show.Models.*;
import com.example.book_my_show.Repository.*;
import com.example.book_my_show.RequestDTO.BookTicketRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    TheaterRepository theaterRepository;

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
        Shows shows = findRightShow(bookTicketRequestDTO);

        //we got right show

        User user = userRepository.findByMobileNo(bookTicketRequestDTO.getMobileNo());


        List<ShowSeat> showSeatList = shows.getShowSeatList();
        int totalPrice = 0;
        for (ShowSeat showSeat : showSeatList) {
            if (bookTicketRequestDTO.getSeatNumbers().contains(showSeat.getSeatNo())) {
                showSeat.setSeatAvailable(false);
                totalPrice+= showSeat.getPrice();
            }
        }
            Ticket ticket = Ticket.builder()
                    .price(totalPrice)
                    .movieName(shows.getMovie().getName())
                    .showDate(shows.getShowDate())
                    .showTime(shows.getShowTime())
                    .seatNo(bookTicketRequestDTO.getSeatNumbers().toString())
                    .theaterAdress(shows.getTheater().getAddress())
                    .show(shows)
                    .user(user)
                    .build();
            shows.getTicketList().add(ticket);
            user.getTicketList().add(ticket);

            ticketRepository.save(ticket);

            return "ticket booked";

    }


        public Shows findRightShow (BookTicketRequestDTO bookTicketRequestDTO){
            Movie movie = movieRepository.findByName(bookTicketRequestDTO.getMovieName());
            Theater theater = theaterRepository.findById(bookTicketRequestDTO.getTheaterId()).get();

            Shows shows = showRepository.findShowByShowTimeAndShowDateAndMovieAndTheater(bookTicketRequestDTO.getShowTime()
                    , bookTicketRequestDTO.getShowDate(), movie, theater);

            return shows;
        }
    }