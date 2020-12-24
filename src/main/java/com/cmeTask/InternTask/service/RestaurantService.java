package com.cmeTask.InternTask.service;

import com.cmeTask.InternTask.dao.RestaurantDao;
import com.cmeTask.InternTask.model.Restaurant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantService {
    private final RestaurantDao restaurantDao;

    public RestaurantService(@Qualifier("postgresRestaurant") RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    public int addRestaurant(Restaurant restaurant){
       return restaurantDao.insertRestaurant(restaurant);
    }
    public List<Restaurant> getAllRestaurant(){
        return restaurantDao.selectAllRestaurant();
    }
    public Optional<Restaurant> getRestaurantById(UUID id){
        return restaurantDao.selectRestaurantById(id);
    }
    public List<Restaurant> getrestaurantByType(String type_id){
        return restaurantDao.getRestaurantByType(type_id);
    }
}
