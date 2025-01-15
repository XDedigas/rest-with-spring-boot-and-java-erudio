package br.com.erudio.rest_with_spring_boot_and_java_erudio.unittests.mapper.mocks;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.PersonVO;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {
    public Person mockEntity(){
        return mockEntity(0);
    }

    public PersonVO mockVO(){
        return mockVO(0);
    }

    public List<Person> mockEntityList(){
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++){
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonVO> mockVOList(){
        List<PersonVO> personsVO = new ArrayList<>();
        for (int i = 0; i < 14; i++){
            personsVO.add(mockVO(i));
        }
        return personsVO;
    }

    public Person mockEntity(Integer number){
        Person person = new Person();
        person.setId(number.longValue());
        person.setGender(((number % 2) == 0) ? "Male" : "Female");
        person.setAddress("Addres Test " + number);
        person.setFirstName("First Name Test: " + number);
        person.setLastName("Last Name Test: " + number);
        return person;
    }

    public PersonVO mockVO(Integer number){
        PersonVO personVO = new PersonVO();
        personVO.setId(number.longValue());
        personVO.setGender(((number % 2) == 0) ? "Male" : "Female");
        personVO.setAddress("Addres Test " + number);
        personVO.setFirstName("First Name Test: " + number);
        personVO.setLastName("Last Name Test: " + number);
        return personVO;
    }
}
