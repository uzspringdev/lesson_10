package com.pro.lesson_10.controller;

import com.pro.lesson_10.entity.Hotel;
import com.pro.lesson_10.entity.Room;
import com.pro.lesson_10.payload.RoomDto;
import com.pro.lesson_10.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/room")
public class RoomController {
    //INJECT SERVICE
    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Room> add(@RequestBody RoomDto roomDto) {
        return service.add(roomDto);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<Room>> getPage(@RequestParam Integer page) {
        return service.getPage(page, 10);
    }

    @GetMapping(value = "/hotel/{hotelId}")
    public ResponseEntity<Page<Room>> getAllByHotelId(@PathVariable Long hotelId, @RequestParam Integer page) {
        return service.getAllByHotelId(hotelId, page, 10);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Room> update(@PathVariable Long id, @RequestBody RoomDto roomDto) {
        return service.update(id, roomDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
