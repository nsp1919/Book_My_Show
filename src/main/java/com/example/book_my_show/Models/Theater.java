package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.dialect.function.HypotheticalSetFunction;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer theaterId;

    String name;

    String address;

    String city;


    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)

    List<TheaterSeat>theaterSeatList=new ArrayList<>();

    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    List<Shows>showsList=new ArrayList<>();

}
