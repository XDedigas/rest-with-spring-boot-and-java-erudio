package br.com.erudio.rest_with_spring_boot_and_java_erudio.mapper.custom;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.PersonVO;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {
    public PersonVO convertEntityToVO(Person person){
        PersonVO vo = new PersonVO();

        vo.setKey(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        return vo;
    }

    public Person convertVOToEntity(PersonVO personVO){
        Person entity = new Person();

        entity.setId(personVO.getKey());
        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());
        return entity;
    }
}
