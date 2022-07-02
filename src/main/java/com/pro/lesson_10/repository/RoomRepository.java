package com.pro.lesson_10.repository;

import com.pro.lesson_10.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> getAllByHotelId(Long hotelId);
}
