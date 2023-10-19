package com.example.Librarymanagementsystem.Repositories;

import com.example.Librarymanagementsystem.Entities.Book;
import com.example.Librarymanagementsystem.Entities.LibraryCard;
import com.example.Librarymanagementsystem.Entities.Transaction;
import com.example.Librarymanagementsystem.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Transaction findTransactionByBookAndLibraryCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus transactionStatus);
}
