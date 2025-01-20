package br.com.erudio.rest_with_spring_boot_and_java_erudio.util;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.BookVO;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.PersonVO;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtils {
    public static Calendar createCalendar(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);  // Garantir que não haja milissegundos
        return calendar;
    }

    // Metodo auxiliar para validar as asserções dos livros
    public static void validateBook(BookVO bookVO, Calendar calendar, int bookId, Boolean verifyServices) {
        calendar.add(Calendar.DAY_OF_MONTH, bookId);  // Ajuste de dias para o item

        assertNotNull(bookVO);
        assertNotNull(bookVO.getKey());
        assertEquals(bookId, bookVO.getKey());
        assertEquals("Title: " + bookId, bookVO.getTitle());
        assertEquals("Author: " + bookId, bookVO.getAuthor());
        assertEquals(calendar.getTime(), bookVO.getLaunchDate());
        assertEquals(BigDecimal.valueOf(bookId * 10L), bookVO.getPrice());

        if (verifyServices){
            assertNotNull(bookVO.getLinks());
            assertTrue(bookVO.toString().contains("[</api/book/v1/" + bookId + ">;rel=\"self\"]"));
        }
    }

    // Metodo auxiliar para validar as asserções das pessoas
    public static void validatePerson(PersonVO personVO, int personId, Boolean verifyServices) {
        assertNotNull(personVO);
        assertNotNull(personVO.getKey());
        assertEquals(personId, personVO.getKey());
        assertEquals("First Name Test: " + personId, personVO.getFirstName());
        assertEquals("Last Name Test: " + personId, personVO.getLastName());
        assertEquals("Addres Test "+ personId, personVO.getAddress());
        assertEquals((personId % 2) == 0 ? "Male" : "Female", personVO.getGender());

        if (verifyServices){
            assertNotNull(personVO.getLinks());
            assertTrue(personVO.toString().contains("[</api/person/v1/" + personId + ">;rel=\"self\"]"));
        }
    }
}
