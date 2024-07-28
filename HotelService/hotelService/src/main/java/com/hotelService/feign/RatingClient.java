package com.hotelService.feign;

import com.hotelService.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingClient {

    @GetMapping("/ratings/getAllRatingByHotelId/{hotelId}")
    List<Rating> getAllRatingByHotelId(@PathVariable("hotelId") String hotelId);

}
