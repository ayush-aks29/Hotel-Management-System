package com.hotelService.controller;

import com.hotelService.entity.Hotel;
import com.hotelService.feign.RatingClient;
import com.hotelService.feign.UserClient;
import com.hotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    //create
    @PostMapping("/addHotel")
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel){
        Hotel savedHotel = hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel);
    }

    //get all hotels
    @GetMapping("/getAllHotels")
    public ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> allHotels = hotelService.getAllHotels();
        return ResponseEntity.ok(allHotels);
    }

    //get single hotel
    @GetMapping("/getHotel/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){
        Hotel hotel = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotel);
    }

    //delete hotel by id
    @DeleteMapping("/deleteHotel/{hotelId}")
    public ResponseEntity<Hotel> deleteHotel(@PathVariable String hotelId){
        Hotel deletedHotel = hotelService.deleteHotel(hotelId);
        return ResponseEntity.status(HttpStatus.OK).body(deletedHotel);
    }

    //update hotel
    @PutMapping("/update/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel){
        Hotel updatedHotel = hotelService.updateHotel(hotelId, hotel);
        return ResponseEntity.ok(updatedHotel);
    }

}
