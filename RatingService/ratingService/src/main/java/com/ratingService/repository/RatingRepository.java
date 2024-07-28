package com.ratingService.repository;

import com.ratingService.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, String> {

    //custom methods
    Optional<List<Rating>> findByUserId(String userId);

    Optional<List<Rating>> findByHotelId(String hotelId);

}
