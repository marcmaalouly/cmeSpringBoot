package com.cmeTask.InternTask.api;

import com.cmeTask.InternTask.model.Restaurant;
import com.cmeTask.InternTask.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/v1/restaurant")
@RestController
@CrossOrigin("*")
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

    @GetMapping(path = "/type/{id}")
    public List<Restaurant> getRestaurantByType(@PathVariable("id") String type_id){
        return restaurantService.getrestaurantByType(type_id);
    }
    @GetMapping(path = "/search/name={name}")
    public List<Restaurant> getRestaurantByName(@PathVariable("name") String name){
        return restaurantService.getRestaurantByName(name);
    }

    @GetMapping(path = "{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable("id") UUID id){
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping(path = "/checkvisit/{id}")
    public List<Restaurant> getNonVisitedRestaurant(@PathVariable("id") String person_id){
        return restaurantService.getNonVisitedRestaurant(person_id);
    }
}
