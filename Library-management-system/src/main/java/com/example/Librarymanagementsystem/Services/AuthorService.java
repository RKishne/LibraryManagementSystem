package com.example.Librarymanagementsystem.Services;

import com.example.Librarymanagementsystem.Entities.Author;
import com.example.Librarymanagementsystem.Exceptions.AuthorNotFoundException;
import com.example.Librarymanagementsystem.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    public String authorAdd(Author author) {
        authorRepository.save(author);
        return "Author added successfully to the DB";
    }

    public List<String> listOfAllAuthorName()  {
        List<Author> authors=authorRepository.findAll();

        List<String> authorName=new ArrayList<>();

        for(Author author:authors){
            authorName.add(author.getAuthorName());
        }
        return authorName;
    }

    public Author findAuthorById(int id) throws Exception {
        Optional<Author> authors=authorRepository.findById(id);

        if(!authors.isPresent()){
            throw new AuthorNotFoundException("Invalid Author ID!!!");
        }
        Author author=authors.get();
        return author;
    }
}
