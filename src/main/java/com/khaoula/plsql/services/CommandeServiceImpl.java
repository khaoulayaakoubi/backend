package com.khaoula.plsql.services;

import com.khaoula.plsql.models.Commande;
import com.khaoula.plsql.services.Interfaces.ICommande;
import com.khaoula.plsql.repository.CommandeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommandeServiceImpl implements ICommande {


    private CommandeRepository commandeRepository;

    @Override
    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande findById(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    @Override
    public Commande save(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public void deleteById(Long id) {
        commandeRepository.deleteById(id);
    }
}
