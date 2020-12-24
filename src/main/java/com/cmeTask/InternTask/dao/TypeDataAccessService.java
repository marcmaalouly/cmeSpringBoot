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
        return null;
    }

    @Override
    public Optional<Type> selectTypeById(UUID id) {
        return Optional.empty();
    }
}
