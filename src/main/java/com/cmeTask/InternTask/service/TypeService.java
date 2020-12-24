package com.cmeTask.InternTask.service;

import com.cmeTask.InternTask.dao.TypeDao;
import com.cmeTask.InternTask.model.Type;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TypeService {
    private final TypeDao typeDao;

    public TypeService(@Qualifier("postgresType") TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    public int addType(Type type){
        return typeDao.insertType(type);
    }

    public List<Type> getAllType(){
        return typeDao.selectAllType();
    }

    public Optional<Type> getTypeById(UUID id){
        return typeDao.selectTypeById(id);
    }
}
