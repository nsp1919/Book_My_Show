package com.example.book_my_show.Repository;

import com.example.book_my_show.Models.Shows;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Shows,Integer> {
}
