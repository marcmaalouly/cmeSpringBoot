package com.cmeTask.InternTask.dao;

import com.cmeTask.InternTask.model.Restaurant;
import com.cmeTask.InternTask.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("postgresRestaurant")
public class RestaurantDataAccessService implements RestaurantDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertRestaurant(UUID id, Restaurant restaurant) {
        final String sql="INSERT INTO restaurant(id,name,address,avgcost,phonenumber,type_id) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,id,restaurant.getName(),restaurant.getAddress(),restaurant.getAvgcost(),restaurant.getPhonenumber(),restaurant.getTypeId());
        return 1;
    }


    @Override
    public List<Restaurant> selectAllRestaurant() {
        return null;
    }

    @Override
    public List<Restaurant> getRestaurantByType(String type_id) {

        //I tried SELECT * from restaurant where type_id=? and id not in (SELECT restaurant_id from visit where restaurant_id=?);
        //But because i was using UUID psql was returning an error that i can't search for a UUID using restaurant_id=...

        final String sql="SELECT * FROM restaurant WHERE type_id=?";
        List<Restaurant> rs= jdbcTemplate.query(sql,new Object[]{type_id},(resultSet, i) -> {
           UUID id= UUID.fromString(resultSet.getString("id"));
           String name=resultSet.getString("name");
           String address=resultSet.getString("address");
           int avgcost=resultSet.getInt("avgcost");
           int phonenumber=resultSet.getInt("phonenumber");
           String typeid=resultSet.getString("type_id");
           return new Restaurant(id,name,address,avgcost,phonenumber,typeid);
        });
        final String sql2="select * from visit where restaurant_id=?";

        List<Restaurant> unVisitedRestaurant= new ArrayList<>(rs);

        for( Restaurant r : rs){
            String ids= String.valueOf(r.getId());

            List<Visit> v=jdbcTemplate.query(sql2,new Object[]{ids},(resultSet, i) -> {
                UUID id= UUID.fromString(resultSet.getString("id"));
                String restaurant_id=resultSet.getString("restaurant_id");
                String person_ids=resultSet.getString("person_id");
                Date date=resultSet.getDate("date");
                return new Visit(id,person_ids,restaurant_id,date);
            });

            for(Visit visit : v){
                String vs= visit.getRestaurant_id();
                if(vs.equals(ids)){
                    unVisitedRestaurant.remove(r);
                }
            }

        }
        if(rs.isEmpty()){
            return null;
        }
        return unVisitedRestaurant;
    }

    @Override
    public Optional<Restaurant> selectRestaurantById(UUID id) {
        final String sql="SELECT * FROM restaurant WHERE id=?";
        Restaurant restaurant= jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID ids = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            int avgcost = resultSet.getInt("avgcost");
            int phonenumber = resultSet.getInt("phonenumber");
            String typeid = resultSet.getString("type_id");
            return new Restaurant(ids, name, address, avgcost, phonenumber, typeid);
        });
        return Optional.ofNullable(restaurant);
    }

    @Override
    public List<Restaurant> getRestaurantByName(String name) {

        //I tried SELECT * from restaurant where name LIKE ? and id not in (SELECT restaurant_id from visit where restaurant_id=?);
        //But because i was using UUID psql was returning an error that i can't search for a UUID using restaurant_id=...

        final String sql="SELECT * FROM restaurant WHERE name LIKE ?";
        List<Restaurant> rs= jdbcTemplate.query(sql,new Object[]{"%"+name+"%"},(resultSet, i) -> {
            UUID id= UUID.fromString(resultSet.getString("id"));
            String names=resultSet.getString("name");
            String address=resultSet.getString("address");
            int avgcost=resultSet.getInt("avgcost");
            int phonenumber=resultSet.getInt("phonenumber");
            String typeid=resultSet.getString("type_id");
            return new Restaurant(id,names,address,avgcost,phonenumber,typeid);
        });

        final String sql2="select * from visit where restaurant_id=?";

        List<Restaurant> unVisitedRestaurant= new ArrayList<>(rs);

        for( Restaurant r : rs){
            String ids= String.valueOf(r.getId());

            List<Visit> v=jdbcTemplate.query(sql2,new Object[]{ids},(resultSet, i) -> {
                UUID id= UUID.fromString(resultSet.getString("id"));
                String restaurant_id=resultSet.getString("restaurant_id");
                String person_ids=resultSet.getString("person_id");
                Date date=resultSet.getDate("date");
                return new Visit(id,person_ids,restaurant_id,date);
            });

            for(Visit visit : v){
                String vs= visit.getRestaurant_id();
                if(vs.equals(ids)){
                    unVisitedRestaurant.remove(r);
                }
            }

        }
        if(rs.isEmpty()){
            return null;
        }
        return unVisitedRestaurant;
    }

    @Override
    public List<Restaurant> getNonVisitedRestaurant(String person_id) {

        //I tried SELECT * from restaurant where id not in (SELECT restaurant_id from visit where restaurant_id=?);
        //But because i was using UUID psql was returning an error that i can't search for a UUID using restaurant_id=...


        final String sql="SELECT * from restaurant";
        List<Restaurant> rs=jdbcTemplate.query(sql,(resultSet, i) -> {
            UUID id= UUID.fromString(resultSet.getString("id"));
            String name=resultSet.getString("name");
            String address=resultSet.getString("address");
            int avgcost=resultSet.getInt("avgcost");
            int phonenumber=resultSet.getInt("phonenumber");
            String typeid=resultSet.getString("type_id");
            return new Restaurant(id,name,address,avgcost,phonenumber,typeid);
        });

        //I used person_id in case we have more than one user (not necessary in my task)

        final String sql2="select * from visit where restaurant_id=? and person_id=?";

        List<Restaurant> unVisitedRestaurant= new ArrayList<>(rs);

        for( Restaurant r : rs){
            String ids= String.valueOf(r.getId());

            List<Visit> v=jdbcTemplate.query(sql2,new Object[]{ids,person_id},(resultSet, i) -> {
                UUID id= UUID.fromString(resultSet.getString("id"));
                String restaurant_id=resultSet.getString("restaurant_id");
                String person_ids=resultSet.getString("person_id");
                Date date=resultSet.getDate("date");
                return new Visit(id,person_ids,restaurant_id,date);
            });

            for(Visit visit : v){
                String vs= visit.getRestaurant_id();
                if(vs.equals(ids)){
                    unVisitedRestaurant.remove(r);
                }
            }

        }
        if(rs.isEmpty()){
            return null;
        }
        return unVisitedRestaurant;
    }
}
