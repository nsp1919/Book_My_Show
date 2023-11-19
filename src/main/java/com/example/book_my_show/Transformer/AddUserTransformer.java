package com.example.book_my_show.Transformer;

import com.example.book_my_show.Models.User;
import com.example.book_my_show.RequestDTO.AddUserRequestDTO;

public class AddUserTransformer {
    public static User convertAddUser(AddUserRequestDTO addUserRequestDTO){
        User user=User.builder()
                .name(addUserRequestDTO.getName())
                .address(addUserRequestDTO.getAddress())
                .mailId(addUserRequestDTO.getMailId())
                .mobileNo(addUserRequestDTO.getMobileNo())
                .build();
        return user;
    }
}
