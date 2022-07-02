package com.pro.lesson_10.repository;

import com.pro.lesson_10.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> getAllByHotelId(Long hotelId, Pageable pageable);

}
