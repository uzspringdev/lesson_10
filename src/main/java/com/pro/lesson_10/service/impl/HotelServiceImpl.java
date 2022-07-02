package com.pro.lesson_10.service.impl;

import com.pro.lesson_10.entity.Address;
import com.pro.lesson_10.entity.Hotel;
import com.pro.lesson_10.payload.HotelDto;
import com.pro.lesson_10.repository.AddressRepository;
import com.pro.lesson_10.repository.HotelRepository;
import com.pro.lesson_10.service.HotelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    //INJECT REPOSITORY
    private final HotelRepository hotelRepository;
    private final AddressRepository addressRepository;

    public HotelServiceImpl(HotelRepository hotelRepository, AddressRepository addressRepository) {
        this.hotelRepository = hotelRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public ResponseEntity<Hotel> add(HotelDto hotelDto) {
        try {
            Optional<Address> optionalAddress = addressRepository.findById(hotelDto.getAddressId());
            if (optionalAddress.isPresent()) {
                Hotel hotel = new Hotel();
                Address address = optionalAddress.get();
                hotel.setName(hotelDto.getName());
                hotel.setAddress(address);

                Hotel savedHotel = hotelRepository.save(hotel);

                return new ResponseEntity<>(savedHotel, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Hotel>> getAll() {
        try {
            List<Hotel> hotelList = hotelRepository.findAll();
            return new ResponseEntity<>(hotelList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Page<Hotel>> getPage(Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Hotel> hotelPage = hotelRepository.findAll(pageable);

            return new ResponseEntity<>(hotelPage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Hotel> getById(Long id) {
        try {
            Optional<Hotel> optionalHotel = hotelRepository.findById(id);
            return optionalHotel.map(
                    hotel -> new ResponseEntity<>(hotel, HttpStatus.OK)).orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Hotel> update(Long id, HotelDto hotelDto) {

        try {
            Optional<Hotel> optionalHotel = hotelRepository.findById(id);
            Optional<Address> optionalAddress = addressRepository.findById(hotelDto.getAddressId());
            if (optionalHotel.isPresent() && optionalAddress.isPresent()) {
                Hotel editedHotel = optionalHotel.get();
                editedHotel.setName(hotelDto.getName());
                editedHotel.setAddress(optionalAddress.get());

                Hotel savedHotel = hotelRepository.save(editedHotel);
                return new ResponseEntity<>(savedHotel, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        try {
            if (hotelRepository.existsById(id)) {
                hotelRepository.deleteById(id);
                return new ResponseEntity<>("Hotel is deleted", HttpStatus.OK);
            } else return new ResponseEntity<>("Hotel is not fount", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
