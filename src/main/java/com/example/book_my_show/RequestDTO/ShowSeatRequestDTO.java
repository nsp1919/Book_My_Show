package com.example.book_my_show.RequestDTO;


import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatRequestDTO {

    Integer showId;

    int priceOfClassicSeats;

    int priceOfPremiumSeats;

    int priceOfGoldSeats;

    int priceOfSilverSeats;



}
