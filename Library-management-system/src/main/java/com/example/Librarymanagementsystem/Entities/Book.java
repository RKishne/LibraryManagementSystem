package com.example.Librarymanagementsystem.Entities;

import com.example.Librarymanagementsystem.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    private String bookName;

    private int price;

    private int noOfPages;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private double rating;

    private boolean isAvailable;

    @ManyToOne
    @JoinColumn
    private Author author;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transaction> transactionList=new ArrayList<>();
}



