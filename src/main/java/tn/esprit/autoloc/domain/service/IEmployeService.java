package tn.esprit.autoloc.domain.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface IEmployeService {
    Employe ajouterEmploye(Employe employe);
    Employe modifierEmploye(Employe employe);
    List<Employe> consulterEmployes();
    Employe consulterEmployeById(Long idEmploye);
    void supprimerEmployeById(Long idEmploye);
    Employe desaffecterEmploye(Long idEmploye);
    Employe affecterEmploye(Long idEmploye, Long idAgence);
}
