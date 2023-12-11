package com.example.book_my_show.ResponseDTO;


import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterDetailsResponse {

    String theaterName;

    String theaterAddress;

    String city;
}
