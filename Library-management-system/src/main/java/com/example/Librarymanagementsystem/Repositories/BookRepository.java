package com.example.Librarymanagementsystem.Repositories;

import com.example.Librarymanagementsystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
