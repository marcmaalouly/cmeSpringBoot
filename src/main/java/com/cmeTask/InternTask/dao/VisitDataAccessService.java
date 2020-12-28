package com.cmeTask.InternTask.dao;

import com.cmeTask.InternTask.model.RestVisit;
import com.cmeTask.InternTask.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository("postgresVisit")
public class VisitDataAccessService implements VisitDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertVisit(UUID id, Visit visit) {
        final String sql="INSERT INTO visit(id,person_id,restaurant_id,date) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql,id,visit.getPerson_id(),visit.getRestaurant_id(),visit.getDate());
        return 0;
    }

    @Override
    public List<RestVisit> getVisitUser(String user_id) {
        final String sql="SELECT * FROM visit WHERE person_id=?";
        List<Visit> v= jdbcTemplate.query(sql,new Object[]{user_id},(resultSet, i) -> {
            UUID id=UUID.fromString(resultSet.getString("id"));
            String person_id=resultSet.getString("person_id");
            String restaurant_id=resultSet.getString("restaurant_id");
            Date date=resultSet.getDate("date");
            return new Visit(id,person_id,restaurant_id,date);
        });
        List<RestVisit> restVisits= new ArrayList<>();
        for(Visit visit : v){
            String ids=visit.getRestaurant_id();
            final String sql2="SELECT name FROM restaurant WHERE id::text=?";
            String rname=jdbcTemplate.queryForObject(sql2,new Object[]{ids},(resultSet, i) -> {
                String name=resultSet.getString("name");
                return name;
            });
            RestVisit rv= new RestVisit(rname,visit.getDate());
            restVisits.add(rv);
        }
        return restVisits;
    }

}
