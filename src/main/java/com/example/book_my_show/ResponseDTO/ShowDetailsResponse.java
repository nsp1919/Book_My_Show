package com.example.book_my_show.ResponseDTO;

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
public class ShowDetailsResponse {
    LocalDate showDate;

    LocalTime showTime;

    MovieDetailsResponse movieDetailsResponse;

    TheaterDetailsResponse theaterDetailsResponse;

}
