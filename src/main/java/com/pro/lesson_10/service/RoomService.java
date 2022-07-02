package com.pro.lesson_10.service;

import com.pro.lesson_10.entity.Hotel;
import com.pro.lesson_10.entity.Room;
import com.pro.lesson_10.payload.HotelDto;
import com.pro.lesson_10.payload.RoomDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomService {

    //ADD HOTEL
    ResponseEntity<Room> add(RoomDto roomDto);

    //GET ALL ROOMS
    ResponseEntity<List<Room>> getAll();

    //GET PAGEABLE ROOMS
    ResponseEntity<Page<Room>> getPage(Integer page, Integer size);

    //GET ROOM BY ID
    ResponseEntity<Room> getById(Long id);

    //GET ROOM BY HOTEL ID
    ResponseEntity<Page<Room>> getAllByHotelId(Long hotelId, Integer page, Integer size);

    //UPDATE ROOM
    ResponseEntity<Room> update(Long id, RoomDto roomDto);

    //DELETE ROOM
    ResponseEntity<String> delete(Long id);

}
