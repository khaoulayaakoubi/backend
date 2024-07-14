package com.khaoula.plsql.configuration;


import com.khaoula.plsql.models.LigneDeCommande;
import com.khaoula.plsql.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommandeRequest {
    private User user;
    private String shippingAddress;
    private List<LigneDeCommande> ligneDeCommandes;


}
