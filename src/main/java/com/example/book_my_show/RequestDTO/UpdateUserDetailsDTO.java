package com.example.book_my_show.RequestDTO;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDetailsDTO {
    String userId;

    String name;

    String address;
}
