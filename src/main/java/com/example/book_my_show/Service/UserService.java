package com.example.book_my_show.Service;

import com.example.book_my_show.Enums.BookingStatus;
import com.example.book_my_show.Exceptions.TicketAlreadyCanCelled;
import com.example.book_my_show.Exceptions.TicketNotFoundException;
import com.example.book_my_show.Exceptions.UserAlreadyRegisteredException;
import com.example.book_my_show.Exceptions.UserNotFoundException;
import com.example.book_my_show.Models.ShowSeat;
import com.example.book_my_show.Models.Shows;
import com.example.book_my_show.Models.Ticket;
import com.example.book_my_show.Models.User;
import com.example.book_my_show.Repository.TicketRepository;
import com.example.book_my_show.Repository.UserRepository;
import com.example.book_my_show.RequestDTO.AddUserRequestDTO;
import com.example.book_my_show.RequestDTO.UpdateUserDetailsDTO;
import com.example.book_my_show.ResponseDTO.UserDetailsResponse;
import com.example.book_my_show.Transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    public String addUser(AddUserRequestDTO addUserRequestDTO) {
        User user= UserTransformer.convertAddUser(addUserRequestDTO);
        validateUser(user);
        userRepository.save(user);
        return "User added";
    }

    public void validateUser(User user){
        if (userRepository.existsByMailId(user.getMailId()))
            throw new UserAlreadyRegisteredException("User Already Exists With Email Id");
        if (userRepository.existsByMobileNo(user.getMobileNo()))
            throw new UserAlreadyRegisteredException("User Already Exists With Mobile Number");
        if (userRepository.existsByUserId(user.getUserId()))
            throw new UserAlreadyRegisteredException("User Already Exists With User Id");
    }

    public UserDetailsResponse getUserDetails(String userId) {
        User user=userRepository.findByUserId(userId);
        if (user==null)
            throw new UserNotFoundException("User Not Found with the given UserId");
        return UserTransformer.userDetails(user);
    }

    public String updateUserDetails(UpdateUserDetailsDTO updateUserDetailsDTO) {

        User user=userRepository.findByUserId(updateUserDetailsDTO.getUserId());

        if (user==null)
            throw new UserNotFoundException("User Not Found with the given UserId");

        if (updateUserDetailsDTO.getName()!=null)
            user.setName(updateUserDetailsDTO.getName());
        if (updateUserDetailsDTO.getAddress()!=null)
            user.setAddress(updateUserDetailsDTO.getAddress());

        userRepository.save(user);

        return "User details updated";
    }

    public String cancelTicket(Integer ticketId) {
        Optional<Ticket> optionalTicket=ticketRepository.findById(ticketId);
        if (optionalTicket.isEmpty())
            throw new TicketNotFoundException("Ticket Not found with given Id");

        Ticket ticket=optionalTicket.get();
        if (ticket.getBookingStatus().equals(BookingStatus.CANCELLED))
            throw new TicketAlreadyCanCelled("Your Ticket has baen already cancelled");
        User user=ticket.getUser();
        Shows shows=ticket.getShow();

        if (shows!=null)
            shows.getTicketList().remove(ticket);
        if (user!=null)
            user.getTicketList().remove(ticket);

        List<ShowSeat> showSeatList=shows.getShowSeatList();
        for (ShowSeat showSeat:showSeatList){
            if (ticket.getBookedSeatNos().contains(showSeat.getSeatNo())){
                showSeat.setSeatAvailable(true);
            }
        }
        ticket.setBookingStatus(BookingStatus.CANCELLED);
//        ticketRepository.delete(ticket);
        userRepository.save(user);
        return "ticket has been cancelled ";
    }
}
