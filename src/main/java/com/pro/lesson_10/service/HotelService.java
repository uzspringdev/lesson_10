package com.pro.lesson_10.service;

import com.pro.lesson_10.entity.Hotel;
import com.pro.lesson_10.payload.HotelDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HotelService {

    //ADD HOTEL
    ResponseEntity<Hotel> add(HotelDto hotelDto);

    //GET ALL HOTELS
    ResponseEntity<List<Hotel>> getAll();

    //GET PAGEABLE HOTELS
    ResponseEntity<Page<Hotel>> getPage(Integer page, Integer size);

    //GET HOTEL BY ID
    ResponseEntity<Hotel> getById(Long id);

    //UPDATE HOTEL
    ResponseEntity<Hotel> update(Long id, HotelDto hotelDto);

    //DELETE HOTEL
    ResponseEntity<String> delete(Long id);

}
