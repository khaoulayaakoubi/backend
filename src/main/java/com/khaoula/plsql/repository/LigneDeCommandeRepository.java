package com.khaoula.plsql.repository;

import com.khaoula.plsql.models.LigneDeCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneDeCommandeRepository extends JpaRepository<LigneDeCommande, Long> {
}
