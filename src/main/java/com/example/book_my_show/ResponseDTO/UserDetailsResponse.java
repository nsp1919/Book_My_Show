package com.example.book_my_show.ResponseDTO;


import com.example.book_my_show.Models.Ticket;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsResponse {

    String userId;

    String mobileNo;

    String name;

    String address;

    String mailId;

    List<TicketDetailsResponse> ticketList;

}
