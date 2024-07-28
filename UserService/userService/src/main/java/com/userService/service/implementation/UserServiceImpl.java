package com.userService.service.implementation;

import com.userService.entity.Rating;
import com.userService.entity.User;
import com.userService.exceptions.ResourceNotFoundException;
import com.userService.feign.RatingClient;
import com.userService.repository.UserRepository;
import com.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private RatingClient ratingClient;

    @Override
    public User saveUser(User user) {

        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users =  userRepository.findAll();
        for (User user : users) {
            List<Rating> ratings = ratingClient.getAllRatingByUserId(user.getUserId());
            user.setRatings(ratings);
        }

        return users;
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException
                        ("User not found with Id: " +userId));

    }

    @Override
    public User deleteUser(String userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return user;
        }).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id : " + userId));
    }

    @Override
    public User updateUser(String userId, User userDetails) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found with id : " + userId));
        user.setName(userDetails.getName());
        user.setEmailId(userDetails.getEmailId());
        return userRepository.save(user);
    }

}
