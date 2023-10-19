package com.example.Librarymanagementsystem.Services;

import com.example.Librarymanagementsystem.Entities.Author;
import com.example.Librarymanagementsystem.Entities.Book;
import com.example.Librarymanagementsystem.Exceptions.AuthorNotFoundException;
import com.example.Librarymanagementsystem.Repositories.AuthorRepository;
import com.example.Librarymanagementsystem.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    public String addBook(Book book, int authorId) throws Exception {
        Optional<Author> optionalAuthor=authorRepository.findById(authorId);
        if(!optionalAuthor.isPresent()){
            throw new AuthorNotFoundException("Author Id is invalid!!");
        }
        Author author=optionalAuthor.get();

        book.setAuthor(author);

        author.getBookList().add(book);

        authorRepository.save(author);
        return "Book has been added to the DB";

    }

    public List<String> listOfBooksNameAssociatedWithAuthor(int authorId) {
        Author author=authorRepository.findById(authorId).get();
        List<Book> Books=author.getBookList();
        List<String> booksName=new ArrayList<>();

        for(Book book :Books){
            booksName.add(book.getBookName());
        }
        return booksName;
    }
}
