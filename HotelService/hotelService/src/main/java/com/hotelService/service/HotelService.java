package com.hotelService.service;

import com.hotelService.entity.Hotel;

import java.util.List;

public interface HotelService {

    //create hotel
    Hotel createHotel(Hotel hotel);

    //get all hotel
    List<Hotel> getAllHotels();

    //get Hotel by id
    Hotel getHotelById(String hotelId);

    //delete hotel
    Hotel deleteHotel(String hotelId);

    //update hotel
    Hotel updateHotel(String hotelId, Hotel hotel);

}
