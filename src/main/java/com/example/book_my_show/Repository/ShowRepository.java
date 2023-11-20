package com.example.book_my_show.Repository;

import com.example.book_my_show.Models.Movie;
import com.example.book_my_show.Models.Shows;
import com.example.book_my_show.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ShowRepository extends JpaRepository<Shows,Integer> {

    Shows findShowByShowTimeAndShowDateAndMovieAndTheater(LocalTime showTime,
                                                          LocalDate showDate,
                                                          Movie movie,
                                                          Theater theater);
}
