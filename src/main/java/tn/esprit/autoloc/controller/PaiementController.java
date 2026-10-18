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
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.domain.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {
    private final IPaiementService paiementService;

    @PostMapping
    public ResponseEntity<Paiement> ajouter(@RequestBody Paiement paiement) {
        return ResponseEntity.ok(paiementService.ajouterPaiement(paiement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paiement> modifier(@PathVariable Long id, @RequestBody Paiement paiement) {
        if (paiementService.consulterPaiementById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        paiement.setIdPaiement(id);
        return ResponseEntity.ok(paiementService.modifierPaiement(paiement));
    }

    @GetMapping
    public ResponseEntity<List<Paiement>> consulterTous() {
        return ResponseEntity.ok(paiementService.consulterPaiements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> consulterParId(@PathVariable Long id) {
        Paiement paiement = paiementService.consulterPaiementById(id);
        return paiement == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(paiement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (paiementService.consulterPaiementById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        paiementService.supprimerPaiementById(id);
        return ResponseEntity.noContent().build();
    }
}