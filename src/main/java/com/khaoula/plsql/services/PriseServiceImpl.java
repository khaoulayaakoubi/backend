package com.khaoula.plsql.services;

import com.khaoula.plsql.models.User;
import com.khaoula.plsql.repository.UserRepository;
import com.khaoula.plsql.services.Interfaces.IPrise;
import com.khaoula.plsql.repository.PriseRepository;
import com.khaoula.plsql.repository.OrdonnanceRepository;
import com.khaoula.plsql.repository.MedicamentRepository;
import com.khaoula.plsql.models.Prise;
import com.khaoula.plsql.models.Medicament;
import com.khaoula.plsql.models.Ordonnance;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class PriseServiceImpl implements IPrise {
    private OrdonnanceRepository ordonnanceRepository;
    private MedicamentRepository medicamentRepository;
    private PriseRepository priseRepository;
    private UserRepository userRepository;
    @Override
    public Set<Prise> addMedicamentsToOrdonnance(Long ordonnanceId,
                                                 Set<Prise> ordonnanceMedicaments) {
        Optional<Ordonnance> ordonnanceOptional = ordonnanceRepository.findById(ordonnanceId);
        if (!ordonnanceOptional.isPresent()) {
            throw new RuntimeException("Ordonnance not found");
        }
        Ordonnance ordonnance = ordonnanceOptional.get();
        Set<Prise> savedOrdonnanceMedicaments = new HashSet<>();

        for (Prise ordonnanceMedicament : ordonnanceMedicaments) {
            Optional<Medicament> medicamentOptional = medicamentRepository.findById(ordonnanceMedicament.getMedicament().getId());
            if (medicamentOptional.isPresent()) {
                Medicament medicament = medicamentOptional.get();
                ordonnanceMedicament.setOrdonnance(ordonnance);
                ordonnanceMedicament.setMedicament(medicament);
                savedOrdonnanceMedicaments.add(priseRepository.save(ordonnanceMedicament));
            }
        }

        return savedOrdonnanceMedicaments;
    }


    private byte[] generateImageFromPrises(List<Prise> prises) {
        BufferedImage bufferedImage = new BufferedImage(400, 300, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 400, 300);
        g.setColor(Color.BLACK);
        int y = 20;
        for (Prise prise : prises) {
            g.drawString(prise.getMedicament().getTitre() + ": " + prise.getPriseJour() + " / " + prise.getPriseMidi() + " / " + prise.getPriseSoir(), 10, y);
            y += 20;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", baos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    @Override
    public List<Prise> addPrises(Long userId, List<Prise> prises) {
        User user = userRepository.findByUsername(String.valueOf(userId))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Ordonnance ordonnance = new Ordonnance();
        ordonnance.setDateCreation(LocalDate.now());
        ordonnance.setEtat(1);
        ordonnance.setUser(user);

        byte[] image = generateImageFromPrises(prises);
        ordonnance.setPhoto(image);

        ordonnance = ordonnanceRepository.save(ordonnance);

        for (Prise prise : prises) {
            medicamentRepository.findById(prise.getMedicament().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Medicament not found"));

            prise.setOrdonnance(ordonnance);
        }

        return priseRepository.saveAll(prises);
    }

    @Override
    public List<Prise> getMedicamentsByOrdonnance(Long ordonnanceId) {
        return priseRepository.findByOrdonnanceId(ordonnanceId);
    }






}
