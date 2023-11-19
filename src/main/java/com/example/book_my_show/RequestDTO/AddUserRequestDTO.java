package com.example.book_my_show.RequestDTO;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequestDTO {
    int mobileNo;

    String name;

    String address;

    String mailId;
}
