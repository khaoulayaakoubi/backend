package com.khaoula.plsql.services.Interfaces;

import com.khaoula.plsql.models.Medicament;

import java.util.List;

public interface IMedicament {
    List<Medicament> findAll();
    Medicament findById(Long id);
    Medicament save(Medicament medicament);
    void deleteById(Long id);
}
