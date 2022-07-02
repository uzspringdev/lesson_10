package com.pro.lesson_10.repository;

import com.pro.lesson_10.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
