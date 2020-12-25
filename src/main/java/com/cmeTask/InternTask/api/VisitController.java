package com.cmeTask.InternTask.api;

import com.cmeTask.InternTask.model.Visit;
import com.cmeTask.InternTask.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/visit")
@RestController
@CrossOrigin("*")
public class VisitController {
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }
    @PostMapping
    public void addVisit(@Valid @NonNull @RequestBody Visit visit){
        visitService.addVisit(visit);
    }
    @GetMapping(path = "{id}")
    public List<Visit> getVisitUser(@PathVariable("id") String person_id){
        return visitService.getVisitUser(person_id);
    }
}
