package br.com.erudio.rest_with_spring_boot_and_java_erudio.repositories;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
