package com.pro.lesson_10.controller;

import com.pro.lesson_10.entity.Address;
import com.pro.lesson_10.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    //INJECT SERVICE
    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Address> add(@RequestBody Address addressDto) {
        return service.add(addressDto);
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody Address addressDto) {
        return service.update(id, addressDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
