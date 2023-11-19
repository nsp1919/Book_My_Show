package com.example.book_my_show.RequestDTO;

import com.example.book_my_show.Enums.Gener;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AddMovieRequestDTO {

    String name;

    int duration;

    double rating;

    Gener gener;

    LocalDate releaseDate;

}
