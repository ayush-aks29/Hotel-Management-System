package com.ratingService.controller;

import com.ratingService.entity.Rating;
import com.ratingService.feign.HotelClient;
import com.ratingService.feign.UserClient;
import com.ratingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;


    //Add rating
    @PostMapping("/addRating")
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.addRating(rating));
    }

    //get all ratings
    @GetMapping("/getAllRatings")
    public ResponseEntity<List<Rating>> getAllRatings(){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatings());
    }

    //get rating by ratingId
    @GetMapping("/getRating/{ratingId}")
    public ResponseEntity<Rating> getRatingById(@PathVariable String ratingId) {
        Rating rating = ratingService.getRatingById(ratingId);
        return ResponseEntity.ok(rating);
    }

    //get all rating by userID
    @GetMapping("/getAllRatingByUserId/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatingByUserId(userId));
    }

    //get all rating by hotelId
    @GetMapping("/getAllRatingByHotelId/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatingByHotelId(hotelId));
    }

    //delete rating by ratingId
    @DeleteMapping("/delete/{ratingId}")
    public ResponseEntity<Rating> deleteRatingById(@PathVariable String ratingId){
        ratingService.deleteRatingByRatingId(ratingId);
        return ResponseEntity.noContent().build();
    }


}
