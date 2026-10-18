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
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
public class AgenceController {
    private final IAgenceService agenceService;

    @PostMapping
    public ResponseEntity<Agence> ajouter(@RequestBody Agence agence) {
        return ResponseEntity.ok(agenceService.ajouterAgence(agence));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agence> modifier(@PathVariable Long id, @RequestBody Agence agence) {
        if (agenceService.consulterAgenceById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        agence.setIdAgence(id);
        return ResponseEntity.ok(agenceService.modifierAgence(agence));
    }

    @GetMapping
    public ResponseEntity<List<Agence>> consulterTous() {
        return ResponseEntity.ok(agenceService.consulterAgences());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> consulterParId(@PathVariable Long id) {
        Agence agence = agenceService.consulterAgenceById(id);
        return agence == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(agence);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (agenceService.consulterAgenceById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        agenceService.supprimerAgenceById(id);
        return ResponseEntity.noContent().build();
    }
}