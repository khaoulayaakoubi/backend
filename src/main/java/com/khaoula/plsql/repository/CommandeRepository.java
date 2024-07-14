package com.khaoula.plsql.repository;

import com.khaoula.plsql.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
