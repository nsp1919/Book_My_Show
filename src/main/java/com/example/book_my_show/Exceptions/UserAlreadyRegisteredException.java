package com.example.book_my_show.Exceptions;

public class UserAlreadyRegisteredException extends RuntimeException{

    public UserAlreadyRegisteredException(String message){
    super(message);
    }
}
