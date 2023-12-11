package com.example.book_my_show.Service;


import com.example.book_my_show.Exceptions.*;
import com.example.book_my_show.Models.*;
import com.example.book_my_show.Repository.*;
import com.example.book_my_show.RequestDTO.BookTicketRequestDTO;
import com.example.book_my_show.ResponseDTO.TicketDetailsResponse;
import com.example.book_my_show.Transformer.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.book_my_show.Transformer.TicketTransformer.ticketDetailsResponse;

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

    @Autowired
    JavaMailSender javaMailSender;

    public String bookTicket(BookTicketRequestDTO bookTicketRequestDTO) {
        bookTicketRequestDTO.getSeatNumbers();

        Shows shows = findRightShow(bookTicketRequestDTO);

        //we got right show

        User user = userRepository.findByUserId(bookTicketRequestDTO.getUserId());
        if (user==null)
            throw new UserNotFoundException("User is not registered, please register and try again");
        List<ShowSeat> showSeatList = shows.getShowSeatList();
        int totalPrice = 0;
        for (ShowSeat showSeat : showSeatList) {
            if (bookTicketRequestDTO.getSeatNumbers().contains(showSeat.getSeatNo())) {
                if (!showSeat.isSeatAvailable()){
                    throw new SeatAlreadyBookedException(showSeat.getSeatNo()+" is already booked");
                }
                showSeat.setSeatAvailable(false);
                totalPrice+= showSeat.getPrice();
            }
        }
            Ticket ticket= TicketTransformer.convertTicket(shows,totalPrice,bookTicketRequestDTO,user);
            shows.getTicketList().add(ticket);
            user.getTicketList().add(ticket);
            ticketRepository.save(ticket);
            sendMail(user);
            return "ticket booked";

    }


        public Shows findRightShow (BookTicketRequestDTO bookTicketRequestDTO){
                Movie movie = movieRepository.findByName(bookTicketRequestDTO.getMovieName());
           if(movie==null)
                throw new MovieNotFoundException("Movie name is invalid");
            Optional<Theater> optionalTheater = theaterRepository.findById(bookTicketRequestDTO.getTheaterId());
            if (!optionalTheater.isPresent())
                throw new TheaterNotFoundException("Invalid Theater Id");
            Theater theater=optionalTheater.get();
            Shows shows = showRepository.findShowByShowTimeAndShowDateAndMovieAndTheater(bookTicketRequestDTO.getShowTime()
                    , bookTicketRequestDTO.getShowDate(), movie, theater);
            if (shows==null)
                throw new ShowNotFoundException("Shows are unavailable");
            return shows;
        }

        public void sendMail(User user){
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            TicketDetailsResponse ticketDetailsResponse=ticketDetailsResponse(user.getTicketList().get(user.getTicketList().size()-1));
            String bookingDetails=TicketTransformer.ticketToString(ticketDetailsResponse);
            String text= "Dear "+user.getName()+",\n\n" +
                    "Your ticket booking has been successfully confirmed!\n\n" +
                    "Booking Details:\n" +
                    bookingDetails+
                    "\n\nThank you for using our service.";
            simpleMailMessage.setFrom("nspv2.o@gmail.com");
            simpleMailMessage.setTo(user.getMailId());
            simpleMailMessage.setText(text);
            simpleMailMessage.setSubject("Tickets Booked");
            javaMailSender.send(simpleMailMessage);
        }
    }