package tn.esprit.autoloc.domain.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;

public interface IEquipementService {
    Equipement ajouterEquipement(Equipement equipement);
    Equipement modifierEquipement(Equipement equipement);
    List<Equipement> consulterEquipements();
    Equipement consulterEquipementById(Long idEquipement);
    void supprimerEquipementById(Long idEquipement);
}
