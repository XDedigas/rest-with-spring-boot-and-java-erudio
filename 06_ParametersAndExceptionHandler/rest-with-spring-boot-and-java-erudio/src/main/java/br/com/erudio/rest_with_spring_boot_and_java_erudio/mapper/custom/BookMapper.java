package br.com.erudio.rest_with_spring_boot_and_java_erudio.mapper.custom;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.BookVO;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public BookVO convertEntityToVO(Book book){
        BookVO vo = new BookVO();

        vo.setKey(book.getId());
        vo.setTitle(book.getTitle());
        vo.setAuthor(book.getAuthor());
        vo.setLaunchDate(book.getLaunchDate());
        vo.setPrice(book.getPrice());
        return vo;
    }

    public Book convertVOToEntity(BookVO bookVO){
        Book entity = new Book();

        entity.setId(bookVO.getKey());
        entity.setTitle(bookVO.getTitle());
        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunchDate(bookVO.getLaunchDate());
        entity.setPrice(bookVO.getPrice());
        return entity;
    }
}
