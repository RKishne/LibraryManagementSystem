package com.example.Librarymanagementsystem.Entities;

import com.example.Librarymanagementsystem.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trasactionId;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    private double fine;

    private Date returnDate;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date lastModifiedOn;

    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private LibraryCard libraryCard;
}
