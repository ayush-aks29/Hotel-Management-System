package com.ratingService.service;

import com.ratingService.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RatingService {

    //create
    Rating addRating(Rating rating);

    //get all ratings
    List<Rating> getAllRatings();

    //get rating by ratingId
    Rating getRatingById(String ratingId);

    //get all rating by userId
    List<Rating> getAllRatingByUserId(String userId);

    //get all rating by HotelId
    List<Rating> getAllRatingByHotelId(String hotelId);

    //delete rating By ratingId
    void deleteRatingByRatingId(String ratingId);
}
