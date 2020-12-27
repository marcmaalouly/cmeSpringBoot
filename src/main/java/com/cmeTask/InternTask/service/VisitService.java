package com.cmeTask.InternTask.service;

import com.cmeTask.InternTask.dao.VisitDao;
import com.cmeTask.InternTask.model.RestVisit;
import com.cmeTask.InternTask.model.Visit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {
    private final VisitDao visitDao;

    public VisitService(@Qualifier("postgresVisit") VisitDao visitDao) {
        this.visitDao = visitDao;
    }
    public int addVisit(Visit visit){
        return visitDao.insertVisit(visit);
    }
    public List<RestVisit> getVisitUser(String person_id){
        return visitDao.getVisitUser(person_id);
    }
}
