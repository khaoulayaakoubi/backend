package com.khaoula.plsql.services;


import com.khaoula.plsql.models.User;
import com.khaoula.plsql.repository.OrdonnanceRepository;
import com.khaoula.plsql.repository.MedicamentRepository;
import com.khaoula.plsql.services.Interfaces.IOrdonnance;
import com.khaoula.plsql.repository.UserRepository;
import com.khaoula.plsql.models.Ordonnance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class OrdonnanceServiceImpl implements IOrdonnance {
    private OrdonnanceRepository ordonnanceRepository;
    private UserRepository userRepository;
    private MedicamentRepository medicamentRepository;

    @Override
    public Ordonnance addOrdonnance(Ordonnance ordonnance) {
        return ordonnanceRepository.save(ordonnance);
    }

    @Override
    public List<Ordonnance> getOrdonnancesByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found for id: " + userId);
        }
        return ordonnanceRepository.findByUser(user);
    }

    @Override
    public Ordonnance getOrdonnanceById(Long id) {
        return ordonnanceRepository.findById(id).orElse(null);
    }


}
