package com.khaoula.plsql.repository;

import com.khaoula.plsql.models.Ordonnance;
import com.khaoula.plsql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdonnanceRepository extends JpaRepository<Ordonnance, Long> {
    List<Ordonnance> findByUser(User user);
}

