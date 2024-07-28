package com.ratingService.feign;

import com.ratingService.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="HOTEL-SERVICE")
public interface HotelClient {

    @GetMapping("/hotels/getHotel/{hotelId}")
    Hotel getHotelById(@PathVariable("hotelId") String hotelId);

}
