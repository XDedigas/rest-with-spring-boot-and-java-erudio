package br.com.erudio.rest_with_spring_boot_and_java_erudio.unittests.mockito.services;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.BookVO;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.model.Book;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.repositories.BookRepository;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.services.BookServices;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.unittests.mapper.mocks.MockBook;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest extends Object {

    MockBook input;

    @InjectMocks
    private BookServices service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        // Criando a data com Calendar
        Calendar calendar = TestUtils.createCalendar(2025, Calendar.JANUARY, 1);

        List<Book> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);

        var allBooks = service.findAll();
        assertNotNull(allBooks);
        assertEquals(15, allBooks.size());

        //Realizar testes de alguns livros
        TestUtils.validateBook(allBooks.get(1), (Calendar) calendar.clone(), 1, true);
        TestUtils.validateBook(allBooks.get(4), (Calendar) calendar.clone(), 4, true);
        TestUtils.validateBook(allBooks.get(7), calendar, 7, true);
    }

    @Test
    void findById() {
        Calendar calendar = TestUtils.createCalendar(2025, Calendar.JANUARY, 1);

        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        TestUtils.validateBook(service.findById(1L), calendar, 1, true);
    }

    @Test
    void create() {
        Calendar calendar = TestUtils.createCalendar(2025, Calendar.JANUARY, 1);

        Book entity = input.mockEntity(1);

        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);

        TestUtils.validateBook(service.create(vo), calendar, 1, true);
    }

    @Test
    void createWithNullBook() {
        Exception ex = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = ex.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Calendar calendar = TestUtils.createCalendar(2025, Calendar.JANUARY, 1);

        Book entity = input.mockEntity(1);
        entity.setId(1L);

        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        TestUtils.validateBook(service.update(vo), calendar, 1, true);
    }

    @Test
    void updateWithNullBook() {
        Exception ex = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = ex.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        Calendar calendar = TestUtils.createCalendar(2025, Calendar.JANUARY, 1);

        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.delete(1L);
    }
}