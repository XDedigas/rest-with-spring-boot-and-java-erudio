package br.com.erudio.rest_with_spring_boot_and_java_erudio.services;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.controllers.BookController;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.BookVO;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.mapper.DozerMapper;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.mapper.custom.BookMapper;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.model.Book;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {
    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    @Autowired
    BookMapper mapper;

    public List<BookVO> findAll(){
        logger.info("Finding all books!");

        var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
        books.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
        return books;
    }

    public BookVO findById(Long id){
        logger.info("Finding one book!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public BookVO create(BookVO bookVO) {
        if (bookVO == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one book!");
        var entity = DozerMapper.parseObject(bookVO, Book.class);
        BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO bookVO) {
        if (bookVO == null) throw new RequiredObjectIsNullException();
        logger.info("Updating one book!");

        var entity = repository.findById(bookVO.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this KEY!"));

        entity.setTitle(bookVO.getTitle());
        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunchDate(bookVO.getLaunchDate());
        entity.setPrice(bookVO.getPrice());

        BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(long id) {
        logger.info("Deleting one book!");

        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
