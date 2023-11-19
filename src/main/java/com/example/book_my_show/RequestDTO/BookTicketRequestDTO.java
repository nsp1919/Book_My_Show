package com.example.book_my_show.RequestDTO;


import com.example.book_my_show.Models.Theater;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookTicketRequestDTO {

//    Integer theaterId;

    String movieName;

    String seatNo;

    Integer mobileNo;

    Integer showId;

}
