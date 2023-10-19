package com.example.Librarymanagementsystem.Controllers;

import com.example.Librarymanagementsystem.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;
    @PostMapping("/generatedPlainCard")
    public ResponseEntity generateCard(){
         String response=cardService.generateCard();
         return new ResponseEntity( response, HttpStatus.OK);
    }
    @PutMapping("/associateStudentWithCard")
    public ResponseEntity associateCard(@RequestParam("studentId") int studentId,@RequestParam("CardId") int cardId){
        String response=cardService.associateCard(studentId, cardId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
