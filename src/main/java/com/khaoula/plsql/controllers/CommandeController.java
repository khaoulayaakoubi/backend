package com.khaoula.plsql.controllers;


import com.khaoula.plsql.models.Commande;
import com.khaoula.plsql.services.Interfaces.ICommande;
import com.khaoula.plsql.services.Interfaces.ILigneDeCommande;
import com.khaoula.plsql.models.LigneDeCommande;
import com.khaoula.plsql.configuration.CommandeRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/commandes")
public class CommandeController {

    @Autowired
    private ICommande commandeService;

    @Autowired
    private ILigneDeCommande ligneDeCommandeService;



    @PostMapping("/create")
    public Commande createCommande(@RequestBody CommandeRequest commandeRequest) {
        // Create a new Commande
        Commande newCommande = new Commande();
        newCommande.setDateOperation(new Date());
        newCommande.setEtatLivraison("Pending"); // or other initial state
        newCommande.setUser(commandeRequest.getUser());
        newCommande.setShippingAddress(commandeRequest.getShippingAddress());

        // Save the Commande
        Commande savedCommande = commandeService.save(newCommande);

        // Create and save LigneDeCommandes
        List<LigneDeCommande> ligneDeCommandes = new ArrayList<>();
        for (LigneDeCommande ligne : commandeRequest.getLigneDeCommandes()) {
            ligne.setCommande(savedCommande);
            LigneDeCommande savedLigneDeCommande = ligneDeCommandeService.save(ligne);
            ligneDeCommandes.add(savedLigneDeCommande); // Add saved ligne to list
        }

        savedCommande.setLigneDeCommandes(ligneDeCommandes);


        return savedCommande;
    }
}
