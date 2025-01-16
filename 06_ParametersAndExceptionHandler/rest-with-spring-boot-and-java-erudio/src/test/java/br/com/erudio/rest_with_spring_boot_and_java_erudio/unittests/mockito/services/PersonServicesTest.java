package br.com.erudio.rest_with_spring_boot_and_java_erudio.unittests.mockito.services;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.PersonVO;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.model.Person;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.repositories.PersonRepository;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.services.PersonServices;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.unittests.mapper.mocks.MockPerson;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest extends Object {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();
        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);
        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());
        assertTrue(personOne.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test: 1", personOne.getFirstName());
        assertEquals("Last Name Test: 1", personOne.getLastName());
        assertEquals("Addres Test 1", personOne.getAddress());
        assertEquals("Female", personOne.getGender());

        var personFour = people.get(4);
        assertNotNull(personFour);
        assertNotNull(personFour.getKey());
        assertNotNull(personFour.getLinks());
        assertTrue(personFour.toString().contains("[</api/person/v1/4>;rel=\"self\"]"));
        assertEquals("First Name Test: 4", personFour.getFirstName());
        assertEquals("Last Name Test: 4", personFour.getLastName());
        assertEquals("Addres Test 4", personFour.getAddress());
        assertEquals("Male", personFour.getGender());

        var personSeven = people.get(7);
        assertNotNull(personSeven);
        assertNotNull(personSeven.getKey());
        assertNotNull(personSeven.getLinks());
        assertTrue(personSeven.toString().contains("[</api/person/v1/7>;rel=\"self\"]"));
        assertEquals("First Name Test: 7", personSeven.getFirstName());
        assertEquals("Last Name Test: 7", personSeven.getLastName());
        assertEquals("Addres Test 7", personSeven.getAddress());
        assertEquals("Female", personSeven.getGender());
    }

    @Test
    void findById() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test: 1", result.getFirstName());
        assertEquals("Last Name Test: 1", result.getLastName());
        assertEquals("Addres Test 1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void create() {
        Person entity = input.mockEntity(1);

        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test: 1", result.getFirstName());
        assertEquals("Last Name Test: 1", result.getLastName());
        assertEquals("Addres Test 1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void createWithNullPerson() {
        Exception ex = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = ex.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name Test: 1", result.getFirstName());
        assertEquals("Last Name Test: 1", result.getLastName());
        assertEquals("Addres Test 1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void updateWithNullPerson() {
        Exception ex = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = ex.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.delete(1L);
    }
}