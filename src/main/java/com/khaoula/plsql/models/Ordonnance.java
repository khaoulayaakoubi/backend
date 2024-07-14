package com.khaoula.plsql.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.khaoula.plsql.models.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ordonnance implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Id
    private Long id;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;
    private int etat;
    private LocalDate dateCreation;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "ordonnance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("ordonnance")
    private Set<Prise> ordonnanceMedicaments = new HashSet<>();

}
