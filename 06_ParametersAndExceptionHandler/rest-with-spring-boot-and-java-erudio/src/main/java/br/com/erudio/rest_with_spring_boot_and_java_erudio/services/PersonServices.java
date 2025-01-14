package br.com.erudio.rest_with_spring_boot_and_java_erudio.services;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll(){
        logger.info("Finding all persons!");

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(long id) {
        logger.info("Finding one person!");
        return mockPerson(id);
    }

    public Person create(Person person) {
        logger.info("Creating one person!");
        return person;
    }

    public Person update(Person person) {
        logger.info("Updating one person!");
        return person;
    }

    public void delete(long id) {
        logger.info("Deleting one person!");
    }

    private Person mockPerson(long i){
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Edgar: " + i);
        person.setLastName("Venturini: " + i);
        person.setAddress("Blumenau - Santa Catarina - Brasil: " + i);
        person.setGender("Male");

        return person;
    }
}
