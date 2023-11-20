package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Shows {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    LocalDate showDate;

    LocalTime showTime;

    @OneToMany(mappedBy = "shows",cascade = CascadeType.ALL)
    List<ShowSeat>showSeatList=new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Movie movie;

    @ManyToOne
    @JoinColumn
    Theater theater;

    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    List<Ticket>ticketList=new ArrayList<>();

}
