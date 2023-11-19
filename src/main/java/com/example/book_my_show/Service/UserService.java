package com.example.book_my_show.Service;

import com.example.book_my_show.Models.User;
import com.example.book_my_show.Repository.UserRepository;
import com.example.book_my_show.RequestDTO.AddUserRequestDTO;
import com.example.book_my_show.Transformer.AddUserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public String addUser(AddUserRequestDTO addUserRequestDTO) {
        User user= AddUserTransformer.convertAddUser(addUserRequestDTO);
        userRepository.save(user);
        return "User added";
    }
}
