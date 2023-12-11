package com.example.book_my_show.Models;

import com.example.book_my_show.Enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer bookingId;

    double price;

    String bookedSeatNos;

    LocalDate showDate;

    LocalTime showTime;

    String movieName;

    String theaterName;

    String theaterAdress;

    LocalDate bookedDate;

    @Enumerated(value = EnumType.STRING)
    BookingStatus bookingStatus;

    @ManyToOne
    @JoinColumn
    User user;

    @ManyToOne
    @JoinColumn
    Shows show;



}
