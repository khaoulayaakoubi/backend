package com.khaoula.plsql.services.Interfaces;

import com.khaoula.plsql.models.Commande;
import java.util.List;

public interface ICommande {
    List<Commande> findAll();
    Commande findById(Long id);
    Commande save(Commande commande);
    void deleteById(Long id);
}
