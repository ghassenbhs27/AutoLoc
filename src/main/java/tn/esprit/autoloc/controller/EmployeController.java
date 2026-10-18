package tn.esprit.autoloc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.domain.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeController {
    private final IEmployeService employeService;

    @PostMapping
    public ResponseEntity<Employe> ajouter(@RequestBody Employe employe) {
        return ResponseEntity.ok(employeService.ajouterEmploye(employe));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> modifier(@PathVariable Long id, @RequestBody Employe employe) {
        if (employeService.consulterEmployeById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        employe.setIdEmploye(id);
        return ResponseEntity.ok(employeService.modifierEmploye(employe));
    }

    @GetMapping
    public ResponseEntity<List<Employe>> consulterTous() {
        return ResponseEntity.ok(employeService.consulterEmployes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> consulterParId(@PathVariable Long id) {
        Employe employe = employeService.consulterEmployeById(id);
        return employe == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(employe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (employeService.consulterEmployeById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        employeService.supprimerEmployeById(id);
        return ResponseEntity.noContent().build();
    }
}