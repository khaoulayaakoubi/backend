package com.khaoula.plsql.services.Interfaces;

import com.khaoula.plsql.models.LigneDeCommande;

import java.util.List;

public interface ILigneDeCommande {
    List<LigneDeCommande> findAll();
    LigneDeCommande findById(Long id);
    LigneDeCommande save(LigneDeCommande LigneDeCommande);
    void deleteById(Long id);
}
