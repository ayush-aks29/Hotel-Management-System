package com.ratingService.service.implementation;

import com.ratingService.entity.Hotel;
import com.ratingService.entity.Rating;
import com.ratingService.entity.User;
import com.ratingService.exception.ResourceNotFoundException;
import com.ratingService.feign.HotelClient;
import com.ratingService.feign.UserClient;
import com.ratingService.repository.RatingRepository;
import com.ratingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private HotelClient hotelClient;

    @Override
    public Rating addRating(Rating rating) {
        String randomRatingId = UUID.randomUUID().toString();
        rating.setRatingId(randomRatingId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllRatingByUserId(String userId) {
        List<Rating> ratings = ratingRepository.findByUserId(userId).orElseThrow(()->
                new ResourceNotFoundException("Ratings not found with user id: "+userId));
        for (Rating rating : ratings) {
            Hotel hotel = hotelClient.getHotelById(rating.getHotelId());
            rating.setHotel(hotel);
        }
        return ratings;
    }

    @Override
    public Rating getRatingById(String ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new RuntimeException("Rating not found"));
        User user = userClient.getUserById(rating.getUserId());
        Hotel hotel = hotelClient.getHotelById(rating.getHotelId());
        rating.setUser(user);
        rating.setHotel(hotel);
        return rating;
    }

    @Override
    public List<Rating> getAllRatingByHotelId(String hotelId) {
        List<Rating> ratings = ratingRepository.findByHotelId(hotelId).orElseThrow(()->
                new ResourceNotFoundException("Ratings not found with hotel id: "+hotelId));
        for (Rating rating : ratings) {
            User user = userClient.getUserById(rating.getUserId());
            rating.setUser(user);
        }
        return ratings;
    }

    @Override
    public void deleteRatingByRatingId(String ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("Rating not found with id: "+ratingId));
        ratingRepository.delete(rating);
    }
}
