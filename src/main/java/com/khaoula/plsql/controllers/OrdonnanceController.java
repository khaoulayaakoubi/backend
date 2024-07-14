package com.khaoula.plsql.controllers;


import com.khaoula.plsql.models.Ordonnance;
import com.khaoula.plsql.models.Prise;
import com.khaoula.plsql.models.User;
import com.khaoula.plsql.services.Interfaces.IMedicament;
import com.khaoula.plsql.services.Interfaces.IOrdonnance;
import com.khaoula.plsql.services.Interfaces.IPrise;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ordonnances")
public class OrdonnanceController {
    private IOrdonnance iOrdonnance;
    private IMedicament iMedicament;
    private IPrise iPrise;
    @PostMapping("/add")
    public ResponseEntity<Ordonnance> addOrdonnance(
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("userId") Long userId
    ) {
        User user = new User (userId, null);
        try {
            Ordonnance ordonnance = new Ordonnance();
            ordonnance.setEtat(1);
            ordonnance.setDateCreation(LocalDate.now());
            ordonnance.setPhoto(photo.getBytes());
            ordonnance.setUser(user);
            Ordonnance savedOrdonnance = iOrdonnance.addOrdonnance(ordonnance);
            return new ResponseEntity<>(savedOrdonnance, HttpStatus.CREATED);
        } catch (IOException | EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public List<Ordonnance> getOrdonnancesByUserId(@PathVariable Long userId) {
        return iOrdonnance.getOrdonnancesByUserId(userId);
    }

    @GetMapping("/{id}")
    public Ordonnance getOrdonnanceById(@PathVariable Long id){
        return iOrdonnance.getOrdonnanceById(id);
    }

    @GetMapping("/{ordonnanceId}/medicaments")
    public List<Prise> getMedicamentsByOrdonnance(@PathVariable Long ordonnanceId) {
        return iPrise.getMedicamentsByOrdonnance(ordonnanceId);
    }


}
