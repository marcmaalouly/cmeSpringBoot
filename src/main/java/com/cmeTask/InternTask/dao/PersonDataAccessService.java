package com.cmeTask.InternTask.dao;

import com.cmeTask.InternTask.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertPerson(UUID id, Person person) {
        final String sql="INSERT INTO person(id,name) VALUES(?,?)";
        jdbcTemplate.update(sql,id,person.getName());
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql= "SELECT * FROM person;";
        return jdbcTemplate.query(sql,(resultSet, i) -> {
            UUID id=UUID.fromString(resultSet.getString("id"));
            String name=resultSet.getString("name");
            return new Person(id,name);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql="DELETE FROM person WHERE id=?";
        jdbcTemplate.update(sql,id);
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        final String sql="UPDATE person SET name=? WHERE id=?";
        jdbcTemplate.update(sql,person.getName(),id);
        return 1;
    }
}
