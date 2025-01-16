package br.com.erudio.rest_with_spring_boot_and_java_erudio.services;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.controllers.PersonController;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.PersonVO;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.mapper.DozerMapper;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.mapper.custom.PersonMapper;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.model.Person;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.repositories.PersonRepository;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll(){
        logger.info("Finding all persons!");

        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO personVO) {
        if (personVO == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one person!");
        var entity = DozerMapper.parseObject(personVO, Person.class);
        PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 personVOV2) {
        if (personVOV2 == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one person with v2!");
        var entity = mapper.convertVOToEntity(personVOV2);
        PersonVOV2 vo = mapper.convertEntityToVO(repository.save(entity));
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO personVO) {
        if (personVO == null) throw new RequiredObjectIsNullException();
        logger.info("Updating one person!");

        var entity = repository.findById(personVO.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this KEY!"));

        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());

        PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(long id) {
        logger.info("Deleting one person!");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
