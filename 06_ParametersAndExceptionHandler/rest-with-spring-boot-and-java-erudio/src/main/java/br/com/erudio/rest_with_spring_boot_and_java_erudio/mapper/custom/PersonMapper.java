package br.com.erudio.rest_with_spring_boot_and_java_erudio.mapper.custom;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {
    public PersonVOV2 convertEntityToVO(Person person){
        PersonVOV2 vo = new PersonVOV2();

        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        vo.setBirthDay(new Date());
        return vo;
    }

    public Person convertVOToEntity(PersonVOV2 personVOV2){
        Person entity = new Person();

        entity.setId(personVOV2.getId());
        entity.setFirstName(personVOV2.getFirstName());
        entity.setLastName(personVOV2.getLastName());
        entity.setAddress(personVOV2.getAddress());
        entity.setGender(personVOV2.getGender());
        return entity;
    }
}
