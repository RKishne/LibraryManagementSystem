package com.example.Librarymanagementsystem.Controllers;

import com.example.Librarymanagementsystem.Entities.Book;
import com.example.Librarymanagementsystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody Book book, @RequestParam("authorId") int authorId) throws Exception {
        return bookService.addBook(book,authorId);
    }
    @GetMapping("/listOfBooksNameAssociatedWithAuthor")
    public List<String>  listOfBooksNameAssociatedWithAuthor(@RequestParam("authorId") int authorId){
        return bookService.listOfBooksNameAssociatedWithAuthor(authorId);
    }
}
