package com.example.book_my_show.Exceptions;

public class TheaterNotFoundException extends RuntimeException{

    public TheaterNotFoundException(String message){
        super(message);
    }

}
