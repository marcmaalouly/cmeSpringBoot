package com.cmeTask.InternTask.dao;

import com.cmeTask.InternTask.model.Restaurant;
import com.cmeTask.InternTask.model.Type;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantDao {
    int insertRestaurant(UUID id, Restaurant restaurant);
    default int insertRestaurant(Restaurant restaurant){
        UUID id=UUID.randomUUID();
        return insertRestaurant(id,restaurant);
    }
    List<Restaurant> selectAllRestaurant();
    List<Restaurant> getRestaurantByType(String type_id);
    Optional<Restaurant> selectRestaurantById(UUID id);
    List<Restaurant> getRestaurantByName(String name);
    List<Restaurant> getNonVisitedRestaurant(String person_id);
}
