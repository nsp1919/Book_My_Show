package com.example.book_my_show.ResponseDTO;


import com.example.book_my_show.Enums.BookingStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDetailsResponse {
        LocalDate showDate;

        LocalTime showTime;

        LocalDate bookedDate;

        String movieName;

        String theaterName;

        String theaterAddress;

        String bookedSeatNumbers;

        BookingStatus bookingStatus;

        double price;
}
