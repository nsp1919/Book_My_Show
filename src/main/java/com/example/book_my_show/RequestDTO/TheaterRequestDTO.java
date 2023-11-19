package com.example.book_my_show.RequestDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterRequestDTO {

    String name;

    String address;

    String city;

    int numOfClassicSeats;

    int noOfPremiumSeats;

    int noOfGoldSeats;

    int noOfSilverSeats;

    int noOfSeatsInEachRow;


}
