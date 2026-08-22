// BookController.java
// D. Singletary
// 11/20/24
// Book controller component in MVC example

// Sandhi Sayem - 2026-08-22 - Added more books to the main method to test various scenarios including null values and duplicates.

package edu.cop3330c.bookmanager;

import java.util.List;

// BookController is where our business logic can be found
public class BookController {

    private final BookRepository repository;

    public BookController() {
        this.repository = new BookRepository();
        // Set up the database
        this.repository.initializeDatabase();
    }

    public void addBook(String title, String author) {
        Book book = new Book(null, title, author);
        repository.save(book);
        System.out.println("Book added: " + book);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookById(Long id) {
        return repository.findById(id);
    }

    public void deleteBook(Long id) {
        Book book = repository.findById(id);
        if (book != null) {
            repository.delete(book);
            System.out.println("Book deleted: " + book);
        } else {
            System.out.println("Book with ID " + id + " not found.");
        }
    }

    public static void main(String[] args) {
        BookController controller = new BookController();

        // Add books
        controller.addBook("Effective Java", "Joshua Bloch");
        controller.addBook("Clean Code", "Robert C. Martin");

        // Adding more books to test various scenarios
        controller.addBook("Sandhi Good Path Book", "Sandhi Sayem");
        controller.addBook("Sandhi Just Title", null);
        controller.addBook(null, "Sandhi Just Author");
        controller.addBook("Sandhi Duplicate Book", "Sandhi Duplicate Author");
        controller.addBook("Sandhi Duplicate Book", "Sandhi Duplicate Author");
        controller.addBook(null, null);

        // List all books
        System.out.println("All Books: " + controller.getAllBooks());

        // Find a book by ID
        Book book = controller.getBookById(1L);
        System.out.println("Found Book: " + book);

        // Find books by ID for the newly added books
        System.out.println("Found Good Path Book: " + controller.getBookById(3L));
        System.out.println("Found Book with just title: " + controller.getBookById(4L));
        System.out.println("Found Book with just author: " + controller.getBookById(5L));
        System.out.println("Found Duplicate Book1: " + controller.getBookById(6L));
        System.out.println("Found Duplicate Book2: " + controller.getBookById(7L));
        System.out.println("Found Book with null title and author: " + controller.getBookById(8L));

        // Delete a book
        controller.deleteBook(1L);

        // Attempt to delete books with various scenarios
        controller.deleteBook(4L);
        controller.deleteBook(5L);
        controller.deleteBook(7L);
        controller.deleteBook(8L);
        System.out.println("Books after deletion: " + controller.getAllBooks());
    }
}
