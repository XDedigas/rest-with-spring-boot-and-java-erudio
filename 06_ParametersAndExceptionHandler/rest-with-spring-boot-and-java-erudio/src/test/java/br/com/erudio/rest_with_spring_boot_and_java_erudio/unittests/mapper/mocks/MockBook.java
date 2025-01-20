package br.com.erudio.rest_with_spring_boot_and_java_erudio.unittests.mapper.mocks;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.BookVO;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.model.Book;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MockBook {
    // Data para testes
    private final Date baseDate;

    public MockBook() {
        try {
            // Define uma data fixa para os mocks
            this.baseDate = new SimpleDateFormat("yyyy-MM-dd").parse("2025-01-01");
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao criar data fixa", e);
        }
    }

    public Book mockEntity(){
        return mockEntity(0);
    }

    public BookVO mockVO(){
        return mockVO(0);
    }

    public List<Book> mockEntityList(){
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList(){
        List<BookVO> bookVO = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            bookVO.add(mockVO(i));
        }
        return bookVO;
    }

    public Book mockEntity(Integer number){
        Book book = new Book();
        book.setId(number.longValue());
        book.setTitle("Title: " + number);
        book.setAuthor("Author: " + number);
        book.setLaunchDate(incrementDays(baseDate, number)); // Incrementa os dias
        book.setPrice(BigDecimal.valueOf(number * 10));
        return book;
    }

    public BookVO mockVO(Integer number){
        BookVO bookVO = new BookVO();
        bookVO.setKey(number.longValue());
        bookVO.setTitle("Title: " + number);
        bookVO.setAuthor("Author: " + number);
        bookVO.setLaunchDate(incrementDays(baseDate, number)); // Incrementa os dias
        bookVO.setPrice(BigDecimal.valueOf(number * 10));
        return bookVO;
    }

    // Metodo para incrementar dias a uma data
    private Date incrementDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days); // Incrementa os dias
        return calendar.getTime();
    }
}
