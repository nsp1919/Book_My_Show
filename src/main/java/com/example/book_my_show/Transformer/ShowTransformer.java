package com.example.book_my_show.Transformer;

import com.example.book_my_show.Models.Shows;
import com.example.book_my_show.RequestDTO.AddShowRequestDTO;

public class ShowTransformer {

    public static Shows convertShow(AddShowRequestDTO addShowRequestDTO){

        Shows shows=Shows.builder()
                .showTime(addShowRequestDTO.getShowTime())
                .showDate(addShowRequestDTO.getShowDate())
                .build();

        return shows;

    }

}
