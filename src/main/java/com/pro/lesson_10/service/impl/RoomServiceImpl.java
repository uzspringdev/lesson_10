package com.pro.lesson_10.service.impl;

import com.pro.lesson_10.entity.Hotel;
import com.pro.lesson_10.entity.Room;
import com.pro.lesson_10.payload.RoomDto;
import com.pro.lesson_10.repository.HotelRepository;
import com.pro.lesson_10.repository.RoomRepository;
import com.pro.lesson_10.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    //INJECT REPOSITORIES
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomServiceImpl(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public ResponseEntity<Room> add(RoomDto roomDto) {
        try {
            Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
            if (optionalHotel.isPresent()) {
                Room room = new Room();
                Hotel hotel = optionalHotel.get();

                room.setHotel(hotel);
                room.setFloor(roomDto.getFloor());
                room.setSize(roomDto.getSize());
                room.setNumber(roomDto.getNumber());
                Room savedRoom = roomRepository.save(room);
                return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Room>> getAll() {
        try {
            List<Room> roomList = roomRepository.findAll();
            return new ResponseEntity<>(roomList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Page<Room>> getPage(Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Room> roomPage = roomRepository.findAll(pageable);
            return new ResponseEntity<>(roomPage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Room> getById(Long id) {
        try {
            Optional<Room> optionalRoom = roomRepository.findById(id);
            return optionalRoom.map(room -> new ResponseEntity<>(room, HttpStatus.OK)).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Page<Room>> getAllByHotelId(Long hotelId, Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Room> roomList = roomRepository.getAllByHotelId(hotelId, pageable);

            return new ResponseEntity<>(roomList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Room> update(Long id, RoomDto roomDto) {
        try {
            Optional<Room> optionalRoom = roomRepository.findById(id);
            Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
            if (optionalRoom.isPresent() && optionalHotel.isPresent()) {
                Room editedRoom = optionalRoom.get();
                editedRoom.setHotel(optionalHotel.get());
                editedRoom.setFloor(roomDto.getFloor());
                editedRoom.setNumber(roomDto.getNumber());
                editedRoom.setSize(roomDto.getSize());

                Room savedRoom = roomRepository.save(editedRoom);
                return new ResponseEntity<>(savedRoom, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            if (roomRepository.existsById(id)) {
                roomRepository.deleteById(id);
                return new ResponseEntity<>("Room is deleted", HttpStatus.OK);
            } else return new ResponseEntity<>("Room is not fount", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
