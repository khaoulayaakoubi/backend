package com.khaoula.plsql.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;
import com.khaoula.plsql.models.User;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateOperation;
    private String etatLivraison;
    private String shippingAddress;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<LigneDeCommande> ligneDeCommandes;
}