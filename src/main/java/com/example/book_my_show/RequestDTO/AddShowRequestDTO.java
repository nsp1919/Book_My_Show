package com.example.book_my_show.RequestDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddShowRequestDTO {

    LocalDate showDate;

    LocalTime showTime;

    String movieName;

    Integer theaterId;

}
