package com.cmeTask.InternTask.dao;

import com.cmeTask.InternTask.model.Type;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TypeDao {
    int insertType(UUID id,Type type);
    default int insertType(Type type){
        UUID id=UUID.randomUUID();
        return insertType(id,type);
    }
    List<Type> selectAllType();
    Optional<Type> selectTypeById(UUID id);
}
