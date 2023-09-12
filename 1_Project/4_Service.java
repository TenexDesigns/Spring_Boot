package com.tenex.catalogue.service;




import com.tenex.catalogue.model.Book;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

//@Service
//@Slf4j
//public class CatalogService {
//
//    private final CatalogServiceFeignClient catalogServiceFeignClient;
//
//    @Autowired
//    public CatalogService(CatalogServiceFeignClient catalogServiceFeignClient) {
//        this.catalogServiceFeignClient = catalogServiceFeignClient;
//    }
//
//    public List<Book> getAll() {
//        return catalogServiceFeignClient.getAllBooks();
//    }
//
//    public Book createBook(Book book) {
//        return catalogServiceFeignClient.createBook(book);
//    }
//
//    public Optional<Book> getBookById(String bookId) {
//        return catalogServiceFeignClient.getBookById(bookId);
//    }
//
//    public Optional<Book> updateBook(String bookId, Book updatedBook) {
//        return catalogServiceFeignClient.updateBook(bookId, updatedBook);
//    }
//
//    public void deleteBook(String bookId) {
//        catalogServiceFeignClient.deleteBook(bookId);
//    }
//
//    public List<Book> getAllBooks() {
//        return catalogServiceFeignClient.getAllBooks();
//    }
//}
//----------------------------------------

import com.tenex.catalogue.repository.BookRepository;
import org.springframework.http.ResponseEntity;

@Service
@Slf4j
public class CatalogService {

    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAll(){

        return bookRepository.findAll();


    }

    public Book createBook(Book book) {

        return bookRepository.save(book);


    }

    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String bookId) {
        return  bookRepository.findById(Long.valueOf(bookId));
    }

    public Optional<Book> updateBook(String bookId, Book updatedBook) {

        Optional<Book> optionalBook = bookRepository.findById(Long.valueOf(bookId));

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if (updatedBook.getAuthor() != null) {
                book.setAuthor(updatedBook.getAuthor());
            }
            if (updatedBook.getTitle()!= null) {
                book.setTitle(updatedBook.getTitle());
            }
            if (updatedBook.getPrice() != null) {
                book.setPrice(updatedBook.getPrice());
            }
            if (updatedBook.getStock() != null) {
                book.setStock(updatedBook.getStock());
            }


            return Optional.of(bookRepository.save(book));
        }else {
            return Optional.empty();
        }
    }

    public void deleteBook(String bookId) {


        Optional<Book> employee = bookRepository.findById(Long.valueOf(bookId));
        if (employee.isPresent()) {
            bookRepository.deleteById(Long.valueOf(bookId));
            ResponseEntity.ok("Employee of id " + bookId + " has been deleted");


        }
        //How do I trwo error in spring boot , in what scenarios do I throw errors and how , using what

    }

    public @NotBlank @NotNull @Min(0) Integer getStock(String bookId) {

        Optional<Book> bookOptional = bookRepository.findById(Long.valueOf(bookId));
        if (bookOptional.isPresent()){
            Book book = bookOptional.get();
            return book.getStock();
        }

        return null;
    }
}


