package com.example.book_my_show.Controller;


import com.example.book_my_show.RequestDTO.AddUserRequestDTO;
import com.example.book_my_show.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody AddUserRequestDTO addUserRequestDTO){
        String result= userService.addUser(addUserRequestDTO);
        return new ResponseEntity(result,HttpStatus.OK);
    }
}
