package tn.esprit.autoloc.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class IAgenceServiceImp implements IAgenceService {
    private final AgenceRepository agenceRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    public Agence ajouterAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence modifierAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public List<Agence> consulterAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence consulterAgenceById(Long idAgence) {
        return agenceRepository.findById(idAgence).orElse(null);
    }

    @Override
    public void supprimerAgenceById(Long idAgence) {
        agenceRepository.deleteById(idAgence);
    }

    @Override
    @Transactional
    public Agence ajouterAgenceEtAffecterVehicules(Agence agence) {
        List<Vehicule> vehicules = agence.getVehicules() == null
                ? List.of() : new java.util.ArrayList<>(agence.getVehicules());
        agence.setVehicules(new java.util.ArrayList<>());
        Agence agenceEnregistree = agenceRepository.save(agence);
        for (Vehicule vehicule : vehicules) {
            vehicule.setAgence(agenceEnregistree);
            vehiculeRepository.save(vehicule);
            agenceEnregistree.getVehicules().add(vehicule);
        }
        return agenceEnregistree;
    }
}
