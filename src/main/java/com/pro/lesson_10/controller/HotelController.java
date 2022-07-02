package com.pro.lesson_10.controller;

import com.pro.lesson_10.entity.Hotel;
import com.pro.lesson_10.payload.HotelDto;
import com.pro.lesson_10.service.HotelService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {
    //INJECT SERVICE
    private final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Hotel> add(@RequestBody HotelDto hotelDto) {
        return service.add(hotelDto);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<Hotel>> getPage(@RequestParam Integer page) {
        return service.getPage(page, 10);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Hotel> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Hotel> update(@PathVariable Long id, @RequestBody HotelDto hotelDto) {
        return service.update(id, hotelDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
