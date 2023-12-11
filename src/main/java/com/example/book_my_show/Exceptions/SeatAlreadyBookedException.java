package com.example.book_my_show.Exceptions;

public class SeatAlreadyBookedException extends  RuntimeException{

    public SeatAlreadyBookedException(String message){
        super(message);
    }
}
