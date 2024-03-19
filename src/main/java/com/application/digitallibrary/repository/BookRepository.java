package com.application.digitallibrary.repository;

import com.application.digitallibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

//Jpa Repository-->set of methods for managing entities-->for example Book
// methods: save(entity), findById(id), findAll, deleteById(id), etc.
public interface BookRepository extends JpaRepository<Book, Long> {
}
