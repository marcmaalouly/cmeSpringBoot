package com.cmeTask.InternTask.api;

import com.cmeTask.InternTask.model.Restaurant;
import com.cmeTask.InternTask.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/v1/restaurant")
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public void addRestaurant(@Valid @NonNull @RequestBody Restaurant restaurant){
        restaurantService.addRestaurant(restaurant);
    }
}
