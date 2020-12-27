package com.cmeTask.InternTask.dao;

import com.cmeTask.InternTask.model.RestVisit;
import com.cmeTask.InternTask.model.Visit;

import java.util.List;
import java.util.UUID;

public interface VisitDao {
    int insertVisit(UUID id, Visit visit);
    default int insertVisit(Visit visit){
        UUID id=UUID.randomUUID();
        return insertVisit(id,visit);
    }
    List<RestVisit> getVisitUser(String person_id);
}
