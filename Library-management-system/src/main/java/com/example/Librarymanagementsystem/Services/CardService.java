package com.example.Librarymanagementsystem.Services;

import com.example.Librarymanagementsystem.Entities.LibraryCard;
import com.example.Librarymanagementsystem.Entities.Student;
import com.example.Librarymanagementsystem.Enums.CardStatus;
import com.example.Librarymanagementsystem.Repositories.CardRepository;
import com.example.Librarymanagementsystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private StudentRepository studentRepository;

    public String generateCard(){
        LibraryCard card=new LibraryCard();
        card.setCardStatus(CardStatus.NEW);
        card=cardRepository.save(card);

        return "New Library Card has benn generated Successfully with card Id :-"+card.getCardNo();
    }
    public String associateCard(int studentId,int cardId){
        Optional<Student> optionalStudent=studentRepository.findById(studentId);
        Student student=optionalStudent.get();

        Optional<LibraryCard> optionalLibraryCard=cardRepository.findById(cardId);
        LibraryCard libraryCard=optionalLibraryCard.get();

        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudentName(student.getStudentName());
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);
        studentRepository.save(student);
        return "Card Id is "+cardId+" associated with the studentId is "+studentId;
    }
}
