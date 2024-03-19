package com.application.digitallibrary;

import com.application.digitallibrary.entity.Author;
import com.application.digitallibrary.entity.Book;
import com.application.digitallibrary.entity.Category;
import com.application.digitallibrary.entity.Publisher;
import com.application.digitallibrary.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DigitallibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitallibraryApplication.class, args);
	}


	@Bean
	public CommandLineRunner initialCreate(BookService bookService) {
		return (args) -> {
			Book book1 = new Book("Being and Time", "1438432763", "Existentialism");
			Author author1 = new Author("Martin Heidegger", "German Philosopher");
			Category category1 = new Category("Philosophy");
			Publisher publisher1 = new Publisher("State University of New York Press");
			book1.addAuthor(author1);
			book1.addCategory(category1);
			book1.addPublisher(publisher1);
			bookService.createBook(book1);

			Book book2 = new Book("One Hundred Years of Solitude", "0060883286", "Magical Realism");
			Author author2 = new Author(" Gabriel Garcia Marquez", "Latin American Novelist");
			Category category2 = new Category("Literature");
			Publisher publisher2 = new Publisher("Harper Perennial Modern Classics");
			book2.addAuthor(author2);
			book2.addCategory(category2);
			book2.addPublisher(publisher2);
			bookService.createBook(book2);

			Book book3 = new Book("A Brief History of Time", "978-0553380163", "Popular Science");
			Author author3 = new Author("Stephen Hawking", " Theoretical Physicist");
			Category category3 = new Category("Science");
			Publisher publisher3 = new Publisher("Random House Publishing Group");
			book3.addAuthor(author3);
			book3.addCategory(category3);
			book3.addPublisher(publisher3);
			bookService.createBook(book3);
		};
	}

}
