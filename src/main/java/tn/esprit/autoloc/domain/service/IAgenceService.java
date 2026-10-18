package tn.esprit.autoloc.domain.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {
    Agence ajouterAgence(Agence agence);
    Agence modifierAgence(Agence agence);
    List<Agence> consulterAgences();
    Agence consulterAgenceById(Long idAgence);
    void supprimerAgenceById(Long idAgence);
}
