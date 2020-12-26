package com.cmeTask.InternTask.dao;

import com.cmeTask.InternTask.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresType")
public class TypeDataAccessService implements TypeDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertType(UUID id, Type type) {
        final String sql="INSERT INTO type(id,type) VALUES(?,?)";
        jdbcTemplate.update(sql,id,type.getType());
        return 1;
    }

    @Override
    public List<Type> selectAllType() {
        final String sql="SELECT * FROM type";
        return jdbcTemplate.query(sql,(resultSet, i) -> {
            UUID id=UUID.fromString(resultSet.getString("id"));
            String type=resultSet.getString("type");
            return new Type(id,type);
        });
    }

    @Override
    public Optional<Type> selectTypeById(UUID id) {
        final String sql="SELECT * FROM type WHERE id=?";
        Type type=jdbcTemplate.queryForObject(sql,new Object[]{id},(resultSet, i) -> {
            UUID ids=UUID.fromString(resultSet.getString("id"));
            String types= resultSet.getString("type");
            return new Type(ids,types);
        });
        return Optional.ofNullable(type);
    }
}
