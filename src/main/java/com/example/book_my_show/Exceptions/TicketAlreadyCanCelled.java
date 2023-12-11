package com.example.book_my_show.Exceptions;

public class TicketAlreadyCanCelled extends RuntimeException{

    public TicketAlreadyCanCelled(String message){
        super(message);
    }
}
