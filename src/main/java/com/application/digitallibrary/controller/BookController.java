package com.application.digitallibrary.controller;

import com.application.digitallibrary.entity.Author;
import com.application.digitallibrary.entity.Book;
import com.application.digitallibrary.entity.Category;
import com.application.digitallibrary.entity.Publisher;
import com.application.digitallibrary.service.AuthorService;
import com.application.digitallibrary.service.BookService;
import com.application.digitallibrary.service.CategoryService;
import com.application.digitallibrary.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//PRESENTATION LAYER FOR BOOK:
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private AuthorService authorService;

    //GETTING LIST OF BOOKS AND DISPLAYING IT ON UI:

    @GetMapping("/books")
    public String findAllBooks(Model model){
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books"; //  RETURN HTML "books";
    }
     // VIEW BOOK DETAILS:
    @GetMapping("/book/{id}")
    public String findBook(@PathVariable Long id, Model model){
    Book book = bookService.findBookById(id);
    model.addAttribute("book", book);
    return "list-book";
    }
     // DELETE A BOOK:
    @GetMapping("remove-book/{id}")
    public String deleteBook(@PathVariable Long id, Model model){
        bookService.deleteBook(id);
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
    }
      // EDIT/UPDATE BOOK:
    @GetMapping("/update-book/{id}")
    public String updateBook(@PathVariable Long id, Model model){
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "update-book";
    }
     // SUBMIT/SAVE CHANGES (of the updated book):
    @PostMapping("/save-update/{id}")
    public String updateBook(@PathVariable Long id, Book book, BindingResult result, Model model){
        if(result.hasErrors()){
            return "update-book";
        }
        bookService.updateBook(book);
        model.addAttribute("books", bookService.findAllBooks());
        return "redirect:/books";
    }
      // SELF-EXPLANATORY:
    @GetMapping("/add-book")
    public String addBook( Book book, Model model){
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("publishers", publisherService.findAllPublishers());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "add-book";
    }
       // SUBMIT/SAVE ADDED BOOK
    @PostMapping("/save-book")
    public String saveBook(Book book, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-book";
        }
        bookService.updateBook(book);
        model.addAttribute("books", bookService.findAllBooks());
        return "redirect:/books";
    }
}
