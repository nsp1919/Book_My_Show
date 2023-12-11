package com.example.book_my_show.RequestDTO;


import com.example.book_my_show.Models.Theater;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookTicketRequestDTO {

    Integer theaterId;

    String movieName;

    List<String> seatNumbers;

    String userId;

    LocalDate showDate;

    LocalTime showTime;

}
