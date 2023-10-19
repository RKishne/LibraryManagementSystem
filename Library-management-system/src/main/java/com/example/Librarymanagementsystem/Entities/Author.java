package com.example.Librarymanagementsystem.Entities;

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
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    private String authorName;

    private int age;

    private double rating;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();



}
