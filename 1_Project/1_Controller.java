package com.tenex.catalogue.controller;


import com.tenex.catalogue.model.Book;
import com.tenex.catalogue.service.CatalogService;
import com.tenex.catalogue.service.ReviewModel;
import com.tenex.catalogue.service.ReviewsServiceFeignClient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/books")
public class catalogueController  {

    @Autowired
    private CatalogService catalogService;


    @Autowired
    private ReviewsServiceFeignClient reviewsServiceFeignClient;

// ...

    @GetMapping("/{bookId}/reviews")
    public ResponseEntity<List<ReviewModel>> getReviewsForBook(@PathVariable String bookId) {
        return reviewsServiceFeignClient.getReviewsForBook(bookId);
    }


    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = catalogService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = catalogService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable String bookId) {
        Optional<Book> book = catalogService.getBookById(bookId);
        if (book.isPresent()) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Optional<Book>> updateBook(@PathVariable String bookId, @RequestBody Book updatedBook) {
        Optional<Book> book = catalogService.updateBook(bookId, updatedBook);
        if (book.isPresent()) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        catalogService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/books/{bookId}/stock")
//    public @NotBlank @NotNull @Min(0) Integer getBookStock(@PathVariable String bookId) {
//        // Retrieve and return the stock level for the given book ID
//        return catalogService.getStock(bookId);
//    }

}
