package com.example.book_my_show.Repository;

import com.example.book_my_show.Models.Movie;
import com.example.book_my_show.Models.Shows;
import com.example.book_my_show.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ShowRepository extends JpaRepository<Shows,Integer> {

    Shows findShowByShowTimeAndShowDateAndMovieAndTheater(LocalTime showTime,
                                                          LocalDate showDate,
                                                          Movie movie,
                                                          Theater theater);

    Shows findByShowTime(LocalTime ShowTime);

    @Query(value = "select movie_id from shows group by movie_id order by count(*) desc limit 1",nativeQuery = true)
    Integer getMostShowedMovie(LocalDate reqDate);

    boolean existsByShowDateAndShowTime(LocalDate showDate,LocalTime showTime);
}
