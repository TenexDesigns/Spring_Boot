Using Data Transfer Objects (DTOs) in your controller to update fields in your `BookModel` entity involves a few steps. Here's how you can do it:

Step 1: Create the DTO class (`BookUpdateDTO`) to represent the fields you want to update:

```java
@Data
public class BookUpdateDTO {
    private String title;
    private String author;
    private Integer pages; // Use Integer instead of int to allow null values
}
```

Step 2: Modify your service method to accept the DTO and update the entity:

```java
public Optional<BookModel> update(BookUpdateDTO bookUpdateDTO, int id) {
    Optional<BookModel> optionalBook = bookRepository.findById((long) id);

    if (optionalBook.isPresent()) {
        BookModel book = optionalBook.get();

        if (bookUpdateDTO.getTitle() != null) {
            book.setTitle(bookUpdateDTO.getTitle());
        }

        if (bookUpdateDTO.getAuthor() != null) {
            book.setAuthor(bookUpdateDTO.getAuthor());
        }

        if (bookUpdateDTO.getPages() != null) {
            book.setPages(bookUpdateDTO.getPages());
        }

        return Optional.of(bookRepository.save(book));
    } else {
        return Optional.empty();
    }
}
```

In this updated `update` method, we check if each field in the `BookUpdateDTO` is not null before updating the corresponding field in the `BookModel`. This way, you can update only the fields that are provided in the DTO.

Step 3: Modify your controller to receive and use the DTO:

```java
@PutMapping("{id}")
public ResponseEntity<BookModel> update(@PathVariable int id, @RequestBody BookUpdateDTO bookUpdateDTO) {
    Optional<BookModel> updatedBook = bookService.update(bookUpdateDTO, id);

    if (updatedBook.isPresent()) {
        return ResponseEntity.ok(updatedBook.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}
```

In the controller, you now receive the `BookUpdateDTO` instead of the entire `BookModel` as the request body. This allows you to update only the fields specified in the DTO.

With these changes, you can now send a request to your API with a `BookUpdateDTO` containing only the fields you want to update, and the service will update the corresponding fields in the `BookModel` entity. Fields not provided in the DTO will remain unchanged.



