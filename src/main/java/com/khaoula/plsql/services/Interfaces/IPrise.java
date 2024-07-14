package com.khaoula.plsql.services.Interfaces;

import com.khaoula.plsql.models.Prise;

import java.util.List;
import java.util.Set;

public interface IPrise {
    public Set<Prise> addMedicamentsToOrdonnance(Long ordonnanceId, Set<Prise> ordonnanceMedicaments);

    List<Prise> addPrises(Long userId, List<Prise> prises);

    public List<Prise> getMedicamentsByOrdonnance(Long ordonnanceId);
}
