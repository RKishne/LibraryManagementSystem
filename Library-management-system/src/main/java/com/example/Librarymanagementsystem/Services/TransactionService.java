package com.example.Librarymanagementsystem.Services;

import com.example.Librarymanagementsystem.Entities.Book;
import com.example.Librarymanagementsystem.Entities.LibraryCard;
import com.example.Librarymanagementsystem.Entities.Transaction;
import com.example.Librarymanagementsystem.Enums.CardStatus;
import com.example.Librarymanagementsystem.Enums.TransactionStatus;
import com.example.Librarymanagementsystem.Exceptions.*;
import com.example.Librarymanagementsystem.Repositories.BookRepository;
import com.example.Librarymanagementsystem.Repositories.CardRepository;
import com.example.Librarymanagementsystem.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.lang.*;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private JavaMailSender mailSender;

    private static final Integer MAX_LIMIT_OF_BOOKS = 3;

    private static final Integer FINE_PER_DAY = 5;
    public String issuedBook(int bookId, int cardId) throws BookNotFoundException, BookNotAvailable, CardNotFound, MaxBooksAlreadyIssued, InvalidCardStatusException {

        //create transaction object
        Transaction newTransaction=new Transaction();
        newTransaction.setTransactionStatus(TransactionStatus.PENDING);

        //validation

        //valid bookId
        Optional<Book> optionalBook=bookRepository.findById(bookId);

        if(!optionalBook.isPresent()){
            throw new BookNotFoundException("Book Id entered is not valid");
        }
        Book book=optionalBook.get();
        //Availability of book

        if(book.isAvailable()){
            throw new BookNotAvailable("Book is Unavailable");
        }

        //valid cardID
        Optional<LibraryCard> optionalLibraryCard=cardRepository.findById(cardId);

        if(!optionalLibraryCard.isPresent()){
            throw new CardNotFound("Card Id entered is invalid");
        }
        LibraryCard libraryCard=optionalLibraryCard.get();

        //validate card status
        if(!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)){
            throw new InvalidCardStatusException("Card Status is Not Active");
        }
        // Maximum no of book Issues : MAX_LIMIT = 3
        if(libraryCard.getNoOfBooksIssued()==MAX_LIMIT_OF_BOOKS){
            throw new MaxBooksAlreadyIssued(MAX_LIMIT_OF_BOOKS+"is maximum book is issued already");
        }
        //creating the transaction entity
        newTransaction.setTransactionStatus(TransactionStatus.ISSUED);

        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued()+1);

        book.setAvailable(true);//book is no longer available

        //setting FK
        newTransaction.setBook(book);
        newTransaction.setLibraryCard(libraryCard);

        //saving relevant entities
        book.getTransactionList().add(newTransaction);
        libraryCard.getTransactionList().add(newTransaction);

        //if the parent is more than two for chlid class then insted of saving the parent just save the child entity
        transactionRepository.save(newTransaction);

        SimpleMailMessage mailMessage=new SimpleMailMessage();

        String body="Hi "+libraryCard.getStudentName()+" ! "+"You have successfully issued book whose book id is  "+book.getBookId()+" and book name is "+book.getBookName()+" with your card whose Id is "+libraryCard.getCardNo()+" has issued at "+newTransaction.getCreatedOn();

        mailMessage.setFrom("hr5235577@gmail.com");
        mailMessage.setTo(libraryCard.getStudent().getEmailId());
        mailMessage.setSubject("ThankYou for issuing book with Rahul's library!!!");
        mailMessage.setText(body);

        mailSender.send(mailMessage);

        return "The book with book Id "+bookId+"has been issued to card with "+cardId;
    }

    public String returnBook(int bookId, int cardId) throws BookNotFoundException, CardNotFound {
        //validation

        //validate book
        Optional<Book> optionalBook=bookRepository.findById(bookId);

        if(!optionalBook.isPresent()){
            throw new BookNotFoundException("Book Id entered is invalid");
        }
        Book book=optionalBook.get();

        //validate card
        Optional<LibraryCard> optionalLibraryCard=cardRepository.findById(cardId);

        if(!optionalLibraryCard.isPresent()){
            throw new CardNotFound("Card Id entered is not Valid");
        }
        LibraryCard libraryCard=optionalLibraryCard.get();

        ////I need to find out that issue Transaction

        Transaction transaction=transactionRepository.findTransactionByBookAndLibraryCardAndTransactionStatus(book,libraryCard,TransactionStatus.ISSUED);

        Date issuedDate=transaction.getCreatedOn();

        //calculate days
        long milliseconds=Math.abs(System.currentTimeMillis()-issuedDate.getTime());
        long Days= TimeUnit.DAYS.convert(milliseconds,TimeUnit.MILLISECONDS);

        int fineAmount=0;

        if(Days>15){
            fineAmount= Math.toIntExact((Days - 15) * FINE_PER_DAY);
        }
        Transaction newTransaction=new Transaction();

        newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        newTransaction.setReturnDate(new Date());
        newTransaction.setFine(fineAmount);

        //setting FK
        newTransaction.setBook(book);
        newTransaction.setLibraryCard(libraryCard);

        book.setAvailable(false);
        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued()-1);

        book.getTransactionList().add(newTransaction);
        libraryCard.getTransactionList().add(newTransaction);

        transactionRepository.save(newTransaction);

        SimpleMailMessage mailMessage=new SimpleMailMessage();

        String body="Hi "+libraryCard.getStudentName()+" ! "+"You have successfully returned book whose book id  "+book.getBookId()+" and book name is "+book.getBookName()+"with your card whose Id is "+libraryCard.getCardNo()+" and has return at "+newTransaction.getReturnDate()+" with fine "+transaction.getFine();

        mailMessage.setFrom("hr5235577@gmail.com");
        mailMessage.setTo(libraryCard.getStudent().getEmailId());
        mailMessage.setSubject("ThankYou for issuing book with Rahul's library!!!");
        mailMessage.setText(body);

        mailSender.send(mailMessage);

        return "Book Has been Returned";
    }
}
