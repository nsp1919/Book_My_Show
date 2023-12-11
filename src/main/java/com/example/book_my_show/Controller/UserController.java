package com.example.book_my_show.Controller;


import com.example.book_my_show.Models.User;
import com.example.book_my_show.RequestDTO.AddUserRequestDTO;
import com.example.book_my_show.RequestDTO.UpdateUserDetailsDTO;
import com.example.book_my_show.ResponseDTO.UserDetailsResponse;
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

        try {
            String result = userService.addUser(addUserRequestDTO);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.ALREADY_REPORTED);
        }
    }
    @GetMapping("/user_details")
    public ResponseEntity getUserDetails(@RequestParam("userId") String userId){
        try{
            UserDetailsResponse userDetailsResponse=userService.getUserDetails(userId);
            return new ResponseEntity<>(userDetailsResponse,HttpStatus.OK);
        }
         catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.ALREADY_REPORTED);
        }
    }

    @PutMapping("/update_details")
    public ResponseEntity updateUserDetails(@RequestBody UpdateUserDetailsDTO updateUserDetailsDTO){
        try{
            String response=userService.updateUserDetails(updateUserDetailsDTO);;
            return new ResponseEntity(response,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/cancel_ticket")
    public ResponseEntity cancelTicket(@RequestParam("tickedId") Integer ticketId){
        try{
        String response=userService.cancelTicket(ticketId);
        return new ResponseEntity(response,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
