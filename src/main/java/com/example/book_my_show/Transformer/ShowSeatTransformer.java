package com.example.book_my_show.Transformer;

import com.example.book_my_show.Models.ShowSeat;
import com.example.book_my_show.Models.Shows;
import com.example.book_my_show.Models.TheaterSeat;

public class ShowSeatTransformer {

    public  static ShowSeat createShowSeat(TheaterSeat theaterSeat, Shows shows){
        ShowSeat showSeat= ShowSeat.builder()
                .seatNo(theaterSeat.getSeatNo())
                .seatType(theaterSeat.getSeatType())
                .isSeatAvailable(true)
                .shows(shows)
                .price(0)
                .isFoodAvailable(false)
                .build();
        return showSeat;
    }
}
