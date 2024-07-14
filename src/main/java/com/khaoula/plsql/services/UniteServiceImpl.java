package com.khaoula.plsql.services;

import com.khaoula.plsql.models.Unite;
import com.khaoula.plsql.repository.UniteRepository;
import com.khaoula.plsql.services.Interfaces.IUnite;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UniteServiceImpl implements IUnite {


    private UniteRepository uniteRepository;

    @Override
    public List<Unite> findAll() {
        return uniteRepository.findAll();
    }

    @Override
    public Unite findById(Long id) {
        return uniteRepository.findById(id).orElse(null);
    }

    @Override
    public Unite save(Unite unite) {
        return uniteRepository.save(unite);
    }

    @Override
    public void deleteById(Long id) {
        uniteRepository.deleteById(id);
    }
}
