package com.example.book_my_show.Repository;

import com.example.book_my_show.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByMobileNo(String mobileNum);
    User findByMailId(String mailId);

    User findByUserId(String userId);

    boolean existsByMailId(String mail);

    boolean existsByMobileNo(String MobileNo);

    boolean existsByUserId(String UserId);
}
