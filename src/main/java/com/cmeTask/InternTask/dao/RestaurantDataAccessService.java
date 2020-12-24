package com.cmeTask.InternTask.dao;

import com.cmeTask.InternTask.model.Restaurant;
import com.cmeTask.InternTask.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresRestaurant")
public class RestaurantDataAccessService implements RestaurantDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertRestaurant(UUID id, Restaurant restaurant) {
        final String sql="INSERT INTO restaurant(id,name,address,avgcost,phonenumber,type_id) VALUES(?,?,?,?,?,?)";
        UUID type_id=restaurant.getType().getId();
        jdbcTemplate.update(sql,id,restaurant.getName(),restaurant.getAddress(),restaurant.getAvgcost(),restaurant.getPhonenumber(),type_id);
        return 1;
    }

    @Override
    public List<Restaurant> selectAllRestaurant() {
        return null;
    }

    @Override
    public Optional<Restaurant> selectRestaurantById(UUID id) {
        return Optional.empty();
    }
}
