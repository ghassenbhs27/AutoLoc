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
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.domain.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {
    private final IMaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<Maintenance> ajouter(@RequestBody Maintenance maintenance) {
        return ResponseEntity.ok(maintenanceService.ajouterMaintenance(maintenance));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> modifier(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        if (maintenanceService.consulterMaintenanceById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        maintenance.setIdMaintenance(id);
        return ResponseEntity.ok(maintenanceService.modifierMaintenance(maintenance));
    }

    @GetMapping
    public ResponseEntity<List<Maintenance>> consulterTous() {
        return ResponseEntity.ok(maintenanceService.consulterMaintenances());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> consulterParId(@PathVariable Long id) {
        Maintenance maintenance = maintenanceService.consulterMaintenanceById(id);
        return maintenance == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(maintenance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (maintenanceService.consulterMaintenanceById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        maintenanceService.supprimerMaintenanceById(id);
        return ResponseEntity.noContent().build();
    }
}