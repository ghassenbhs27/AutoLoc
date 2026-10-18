package tn.esprit.autoloc.domain.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

public interface IPaiementService {
    Paiement ajouterPaiement(Paiement paiement);
    Paiement modifierPaiement(Paiement paiement);
    List<Paiement> consulterPaiements();
    Paiement consulterPaiementById(Long idPaiement);
    void supprimerPaiementById(Long idPaiement);
}
