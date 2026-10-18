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
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.domain.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {
    private final IVehiculeService vehiculeService;

    @PostMapping
    public ResponseEntity<Vehicule> ajouter(@RequestBody Vehicule vehicule) {
        return ResponseEntity.ok(vehiculeService.ajouterVehicule(vehicule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> modifier(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        if (vehiculeService.consulterVehiculeById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        vehicule.setIdVehicule(id);
        return ResponseEntity.ok(vehiculeService.modifierVehicule(vehicule));
    }

    @GetMapping
    public ResponseEntity<List<Vehicule>> consulterTous() {
        return ResponseEntity.ok(vehiculeService.consulterVehicules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> consulterParId(@PathVariable Long id) {
        Vehicule vehicule = vehiculeService.consulterVehiculeById(id);
        return vehicule == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(vehicule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (vehiculeService.consulterVehiculeById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        vehiculeService.supprimerVehiculeById(id);
        return ResponseEntity.noContent().build();
    }
}