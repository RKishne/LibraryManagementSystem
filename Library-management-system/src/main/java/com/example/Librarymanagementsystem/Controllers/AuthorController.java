package com.example.Librarymanagementsystem.Controllers;

import com.example.Librarymanagementsystem.Entities.Author;
import com.example.Librarymanagementsystem.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @PostMapping("/add")
    public String authorAdd(@RequestBody Author author){
        return authorService.authorAdd(author);
    }
    @GetMapping("/ListofAllAuthorName")
    public List<String> listOfAllAuthorName() throws Exception{
        List<String> result=authorService.listOfAllAuthorName();
        return result;
    }
    @GetMapping("/findAuthorById/{id}")
    public ResponseEntity findAuthorById(@PathVariable("id") int id) throws Exception{
        try{
            Author author=authorService.findAuthorById(id);
            return new ResponseEntity(author,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
