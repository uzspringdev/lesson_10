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

    //GET ALL HOTELS
    ResponseEntity<List<Room>> getAll();

    //GET PAGEABLE HOTELS
    ResponseEntity<Page<Room>> getPage(Integer page, Integer size);

    //GET HOTEL BY ID
    ResponseEntity<Room> getById(Long id);

    //UPDATE HOTEL
    ResponseEntity<Room> update(Long id, RoomDto roomDto);

    //DELETE HOTEL
    ResponseEntity<String> delete(Long id);

}
