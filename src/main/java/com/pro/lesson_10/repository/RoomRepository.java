package com.pro.lesson_10.repository;

import com.pro.lesson_10.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
