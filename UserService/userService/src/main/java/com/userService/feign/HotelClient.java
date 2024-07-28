package com.userService.feign;

import com.userService.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelClient {

    @GetMapping("/getHotel/{hotelId}")
    Hotel getHotelById(@PathVariable("hotelId") String hotelId);

}
