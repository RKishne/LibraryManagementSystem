package com.example.Librarymanagementsystem.Controllers;

import com.example.Librarymanagementsystem.Exceptions.BookNotAvailable;
import com.example.Librarymanagementsystem.Exceptions.BookNotFoundException;
import com.example.Librarymanagementsystem.Exceptions.CardNotFound;
import com.example.Librarymanagementsystem.Exceptions.MaxBooksAlreadyIssued;
import com.example.Librarymanagementsystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/issuedBook/{bookId}/{cardId}")
    public ResponseEntity issuedBook(@PathVariable("bookId") int bookId,@PathVariable("cardId") int cardId) throws BookNotFoundException, BookNotAvailable, CardNotFound, MaxBooksAlreadyIssued {
        try{
            String result = transactionService.issuedBook(bookId,cardId);
            return new ResponseEntity(result, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/returnBook/{bookId}/{cardId}")
    public ResponseEntity returnBook(@PathVariable("bookId") int bookId,@PathVariable("cardId") int cardId) throws BookNotFoundException, BookNotAvailable, CardNotFound, MaxBooksAlreadyIssued {
        try{
            String result = transactionService.returnBook(bookId,cardId);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
