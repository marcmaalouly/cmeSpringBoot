package com.cmeTask.InternTask.dao;

import com.cmeTask.InternTask.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public List<Visit> getVisitUser(String user_id) {
        return null;
    }
}
