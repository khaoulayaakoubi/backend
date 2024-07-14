package com.khaoula.plsql.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.khaoula.plsql.models.Unite;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Medicament implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private Date dateExpiration;
    private double prix;
    private int quantite;
    private String fabricant;
    private String dosage;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;


    @ManyToOne
    private Unite unite;

    @OneToMany(mappedBy = "medicament", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("medicament")
    private Set<Prise> ordonnanceMedicaments = new HashSet<>();

}
