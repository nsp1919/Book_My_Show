package com.example.book_my_show.Transformer;

import com.example.book_my_show.Enums.BookingStatus;
import com.example.book_my_show.Models.Shows;
import com.example.book_my_show.Models.Ticket;
import com.example.book_my_show.Models.User;
import com.example.book_my_show.RequestDTO.BookTicketRequestDTO;
import com.example.book_my_show.ResponseDTO.TicketDetailsResponse;

import java.time.LocalDate;

public class TicketTransformer {

    public static Ticket convertTicket(Shows shows, int totalPrice, BookTicketRequestDTO bookTicketRequestDTO,User user){
        Ticket ticket = Ticket.builder()
                .price(totalPrice)
                .movieName(shows.getMovie().getName())
                .showDate(shows.getShowDate())
                .showTime(shows.getShowTime())
                .bookedSeatNos(bookTicketRequestDTO.getSeatNumbers().toString())
                .theaterName(shows.getTheater().getName())
                .theaterAdress(shows.getTheater().getAddress())
                .bookedDate(LocalDate.now())
                .bookingStatus(BookingStatus.CONFIRMED)
                .show(shows)
                .user(user)
                .build();

        return ticket;
    }
    public static TicketDetailsResponse ticketDetailsResponse(Ticket ticket){
        TicketDetailsResponse ticketDetailsResponse=TicketDetailsResponse.builder()
                .showDate(ticket.getShowDate())
                .showTime(ticket.getShowTime())
                .bookedDate(ticket.getBookedDate())
                .movieName(ticket.getMovieName())
                .theaterAddress(ticket.getTheaterAdress())
                .theaterName(ticket.getTheaterName())
                .bookedSeatNumbers(ticket.getBookedSeatNos())
                .bookingStatus(ticket.getBookingStatus())
                .price(ticket.getPrice())
                .build();

        return ticketDetailsResponse;
    }

    public static String ticketToString(TicketDetailsResponse ticketDetailsResponse){
        return "ShowDate: "+ticketDetailsResponse.getShowDate()+
                    "\n" +
                    "ShowTime: " +ticketDetailsResponse.getShowTime()+
                    "\n" +
                    "BookedDate: " +ticketDetailsResponse.getBookedDate()+
                    "\n" +
                    "MovieName: " +ticketDetailsResponse.getMovieName()+
                    "\n" +
                    "TheaterName: " +ticketDetailsResponse.getTheaterName()+
                    "\n" +
                    "TheaterAddress:" +ticketDetailsResponse.getTheaterAddress()+
                    "\n" +
                    "BookedSeatNumbers: " +ticketDetailsResponse.getBookedSeatNumbers()+
                    "\n" +
                    "Price: "+ticketDetailsResponse.getPrice();
    }
}
