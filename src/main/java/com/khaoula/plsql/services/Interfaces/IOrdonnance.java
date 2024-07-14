package com.khaoula.plsql.services.Interfaces;

import com.khaoula.plsql.models.Ordonnance;

import java.util.List;

public interface IOrdonnance {
    public Ordonnance addOrdonnance(Ordonnance o);
    List<Ordonnance> getOrdonnancesByUserId(Long userId);
    public Ordonnance getOrdonnanceById (Long id);
}