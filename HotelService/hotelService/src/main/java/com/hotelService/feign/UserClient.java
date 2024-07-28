package com.hotelService.feign;

import com.hotelService.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

    @GetMapping("/users/getUser/{userId}")
    User getUserById(@PathVariable("userId") String userId);

}
