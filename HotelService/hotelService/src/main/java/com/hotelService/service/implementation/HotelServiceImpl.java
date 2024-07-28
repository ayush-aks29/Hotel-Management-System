package com.hotelService.service.implementation;

import com.hotelService.entity.Hotel;
import com.hotelService.entity.Rating;
import com.hotelService.entity.User;
import com.hotelService.exceptions.ResourceNotFoundException;
import com.hotelService.feign.RatingClient;
import com.hotelService.feign.UserClient;
import com.hotelService.repository.HotelRepository;
import com.hotelService.service.HotelService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RatingClient ratingClient;

    @Autowired
    private UserClient userClient;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomUserId = UUID.randomUUID().toString();
        hotel.setHotelId(randomUserId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() ->
                new RuntimeException("Hotel not found with id : "+hotelId));
        List<Rating> ratings = ratingClient.getAllRatingByHotelId(hotelId);
        for (Rating rating : ratings) {
            User user = userClient.getUserById(rating.getUserId());
            rating.setUser(user);
        }
        hotel.setRatings(ratings);
        return hotel;
    }

    @Override
    public Hotel deleteHotel(String hotelId) {
        return hotelRepository.findById(hotelId).map(hotel -> {
            hotelRepository.delete(hotel);
            return hotel;
        }).orElseThrow(() -> new ResourceNotFoundException("hotel not found with id : " + hotelId));
    }

    @Override
    public Hotel updateHotel(String hotelId, Hotel updatedhotelDetails) {
        return hotelRepository.findById(hotelId)
                .map(updatedhotel -> {
                    updatedhotel.setHotelName(updatedhotelDetails.getHotelName());
                    updatedhotel.setLocation(updatedhotelDetails.getLocation());
                    updatedhotel.setAbout(updatedhotelDetails.getAbout());
                    return hotelRepository.save(updatedhotel);
                })
                .orElseThrow(() -> new ResourceNotFoundException("hotel not found with id " + hotelId));
    }


}
