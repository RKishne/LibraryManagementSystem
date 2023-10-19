package com.example.Librarymanagementsystem.Repositories;

import com.example.Librarymanagementsystem.Entities.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard,Integer> {
}
