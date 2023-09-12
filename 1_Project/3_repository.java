package com.tenex.catalogue.repository;

import com.tenex.catalogue.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
