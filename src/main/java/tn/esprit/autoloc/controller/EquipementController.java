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
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
public class EquipementController {
    private final IEquipementService equipementService;

    @PostMapping
    public ResponseEntity<Equipement> ajouter(@RequestBody Equipement equipement) {
        return ResponseEntity.ok(equipementService.ajouterEquipement(equipement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipement> modifier(@PathVariable Long id, @RequestBody Equipement equipement) {
        if (equipementService.consulterEquipementById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        equipement.setIdEquipement(id);
        return ResponseEntity.ok(equipementService.modifierEquipement(equipement));
    }

    @GetMapping
    public ResponseEntity<List<Equipement>> consulterTous() {
        return ResponseEntity.ok(equipementService.consulterEquipements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipement> consulterParId(@PathVariable Long id) {
        Equipement equipement = equipementService.consulterEquipementById(id);
        return equipement == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(equipement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (equipementService.consulterEquipementById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        equipementService.supprimerEquipementById(id);
        return ResponseEntity.noContent().build();
    }
}