package com.khaoula.plsql.repository;

import com.khaoula.plsql.models.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
}

