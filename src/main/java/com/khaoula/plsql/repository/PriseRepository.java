package com.khaoula.plsql.repository;

import com.khaoula.plsql.models.Prise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriseRepository extends JpaRepository<Prise, Long> {
    List<Prise> findByOrdonnanceId(Long ordonnanceId);

}
