package com.example.book_my_show.Models;

import com.example.book_my_show.Enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String seatNo;

    @Enumerated(value = EnumType.STRING)
    SeatType seatType;

    int price;

    boolean isSeatAvailable;

    boolean isFoodAvailable;

    @ManyToOne
    @JoinColumn
    Shows shows;


}
