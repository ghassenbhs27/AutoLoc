package tn.esprit.autoloc.domain.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {
    Maintenance ajouterMaintenance(Maintenance maintenance);
    Maintenance modifierMaintenance(Maintenance maintenance);
    List<Maintenance> consulterMaintenances();
    Maintenance consulterMaintenanceById(Long idMaintenance);
    void supprimerMaintenanceById(Long idMaintenance);
}
