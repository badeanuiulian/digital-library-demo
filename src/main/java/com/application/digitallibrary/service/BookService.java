package com.application.digitallibrary.service;

import com.application.digitallibrary.entity.Book;
import com.application.digitallibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // AUTOMATIC DEPENDENCY INJECTION:
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public Book findBookById(Long id){
       Book book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
        return book;
    }
  // The save(entity) method serves 2 primary purposes:

    //1.INSERTION: if the entity does not exist in the database, it will be inserted as a new record:
    public void createBook(Book book){
        bookRepository.save(book);
    }

    //2.UPDATE: if the entity already exists, any changes made to it will be updated in the database:
    public void updateBook(Book book){bookRepository.save(book);}

      // before deleting a book, you must find it in the database:
    public void deleteBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not found"));
        bookRepository.deleteById(book.getId());
    }

}
