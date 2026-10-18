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
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratController {
    private final IContratService contratService;

    @PostMapping
    public ResponseEntity<Contrat> ajouter(@RequestBody Contrat contrat) {
        return ResponseEntity.ok(contratService.ajouterContrat(contrat));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrat> modifier(@PathVariable Long id, @RequestBody Contrat contrat) {
        if (contratService.consulterContratById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        contrat.setIdContrat(id);
        return ResponseEntity.ok(contratService.modifierContrat(contrat));
    }

    @GetMapping
    public ResponseEntity<List<Contrat>> consulterTous() {
        return ResponseEntity.ok(contratService.consulterContrats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrat> consulterParId(@PathVariable Long id) {
        Contrat contrat = contratService.consulterContratById(id);
        return contrat == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(contrat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (contratService.consulterContratById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        contratService.supprimerContratById(id);
        return ResponseEntity.noContent().build();
    }
}