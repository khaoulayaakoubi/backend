package com.khaoula.plsql.services;


import com.khaoula.plsql.services.Interfaces.IMedicament;
import com.khaoula.plsql.repository.MedicamentRepository;
import com.khaoula.plsql.models.Medicament;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class MedicamentServiceImpl implements IMedicament {


    private MedicamentRepository medicamentRepository;

    @Override
    public List<Medicament> findAll() {
        return medicamentRepository.findAll();
    }

    @Override
    public Medicament findById(Long id) {
        return medicamentRepository.findById(id).orElse(null);
    }

    @Override
    public Medicament save(Medicament medicament) {
        return medicamentRepository.save(medicament);
    }

    @Override
    public void deleteById(Long id) {
        medicamentRepository.deleteById(id);
    }
}
