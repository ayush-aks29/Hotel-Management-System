package com.userService.controller;

import com.userService.entity.Hotel;
import com.userService.entity.Rating;
import com.userService.feign.HotelClient;
import com.userService.feign.RatingClient;
import com.userService.service.UserService;
import com.userService.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RatingClient ratingClient;

    @Autowired
    private HotelClient hotelClient;

    //create
    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    //get single user
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    //get all users
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            List<Rating> ratings = ratingClient.getAllRatingByUserId(user.getUserId());
            for (Rating rating : ratings) {
                Hotel hotel = hotelClient.getHotelById(rating.getHotelName());
                rating.setHotelName(hotel.getName());
            }
            user.setRatings(ratings);
        }

//        return allUsers;
        return ResponseEntity.ok(allUsers);
    }

    //update user
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    //delete user
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable String userId){
        User user = userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


}
