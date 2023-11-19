package com.example.book_my_show.Repository;

import com.example.book_my_show.Enums.SeatType;
import com.example.book_my_show.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Integer> {

    ShowSeat findBySeatNo(String seatNo);
}
