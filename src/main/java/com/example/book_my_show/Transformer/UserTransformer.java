package com.example.book_my_show.Transformer;
import com.example.book_my_show.Models.Ticket;
import com.example.book_my_show.Models.User;
import com.example.book_my_show.RequestDTO.AddUserRequestDTO;
import com.example.book_my_show.ResponseDTO.TicketDetailsResponse;
import com.example.book_my_show.ResponseDTO.UserDetailsResponse;

import java.util.ArrayList;
import java.util.List;

public class UserTransformer {
    public static User convertAddUser(AddUserRequestDTO addUserRequestDTO){
        User user=User.builder()
                .userId(addUserRequestDTO.getUserId())
                .name(addUserRequestDTO.getName())
                .address(addUserRequestDTO.getAddress())
                .mailId(addUserRequestDTO.getMailId())
                .mobileNo(addUserRequestDTO.getMobileNo())
                .build();
        return user;
    }

    public static UserDetailsResponse userDetails(User user){
        UserDetailsResponse userDetailsResponse=UserDetailsResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .address(user.getAddress())
                .mailId(user.getMailId())
                .mobileNo(user.getMobileNo())
                .build();

        List<Ticket>ticketList=user.getTicketList();
        List<TicketDetailsResponse>ticketDetailsResponses=new ArrayList<>();
        for (Ticket ticket:ticketList){
            ticketDetailsResponses.add(TicketTransformer.ticketDetailsResponse(ticket));
        }

        userDetailsResponse.setTicketList(ticketDetailsResponses);

        return userDetailsResponse;
    }
}
