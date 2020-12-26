package com.cmeTask.InternTask.api;

import com.cmeTask.InternTask.model.Type;
import com.cmeTask.InternTask.service.TypeService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/v1/type")
@RestController
@CrossOrigin("*")
public class TypeController {
    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping
    public void addType(@Valid @NonNull @RequestBody Type type){
        typeService.addType(type);
    }

    @GetMapping
    public List<Type> getAllType(){
        return typeService.getAllType();
    }

    @GetMapping(path = "{id}")
    public Optional<Type> getTypeById(@PathVariable("id") UUID id){
        return typeService.getTypeById(id);
    }
}
