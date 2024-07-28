package com.userService.feign;

import com.userService.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="RATING-SERVICE")
public interface RatingClient {

    @GetMapping("/ratings/getAllRatingByUserId/{userId}")
    List<Rating> getAllRatingByUserId(@PathVariable("userId") String userId);


}
