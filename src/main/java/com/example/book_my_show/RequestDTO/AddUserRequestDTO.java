package com.example.book_my_show.RequestDTO;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddUserRequestDTO {
    String userId;

    String mobileNo;

    String name;

    String address;

    String mailId;
}
