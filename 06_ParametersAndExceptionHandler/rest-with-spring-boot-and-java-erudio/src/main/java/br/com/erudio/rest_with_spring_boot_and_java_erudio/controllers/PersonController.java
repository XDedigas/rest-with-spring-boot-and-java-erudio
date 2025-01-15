package br.com.erudio.rest_with_spring_boot_and_java_erudio.controllers;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.PersonVO;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v2.PersonVOV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.services.PersonServices;
import java.util.List;
import static br.com.erudio.rest_with_spring_boot_and_java_erudio.util.MediaType.*;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
    @Autowired
    private PersonServices service;

    @GetMapping(
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @GetMapping(
            value="/{id}",
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
    public PersonVO findById(@PathVariable(value = "id")  long id) {
        return service.findById(id);
    }

    @PostMapping(
            consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML },
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
    public PersonVO create(@RequestBody PersonVO personVO){
        return service.create(personVO);
    }

    @PostMapping(
            value = "/v2",
            consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML },
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
    public PersonVOV2 createV2(@RequestBody PersonVOV2 personVOV2){
        return service.createV2(personVOV2);
    }

    @PutMapping(
            consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML },
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
    public PersonVO update(@RequestBody PersonVO personVO){
        return service.update(personVO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
