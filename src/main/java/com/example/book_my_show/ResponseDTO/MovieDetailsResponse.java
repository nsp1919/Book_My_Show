package com.example.book_my_show.ResponseDTO;

import com.example.book_my_show.Enums.Gener;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDetailsResponse {

    String movieName;

    Integer duration;

    Double rating;

    Gener gener;

    LocalDate releaseDate;
}
