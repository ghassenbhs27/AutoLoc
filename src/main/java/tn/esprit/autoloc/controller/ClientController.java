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
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final IClientService clientService;

    @PostMapping
    public ResponseEntity<Client> ajouter(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.ajouterClient(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> modifier(@PathVariable Long id, @RequestBody Client client) {
        if (clientService.consulterClientById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        client.setIdClient(id);
        return ResponseEntity.ok(clientService.modifierClient(client));
    }

    @GetMapping
    public ResponseEntity<List<Client>> consulterTous() {
        return ResponseEntity.ok(clientService.consulterClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> consulterParId(@PathVariable Long id) {
        Client client = clientService.consulterClientById(id);
        return client == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (clientService.consulterClientById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        clientService.supprimerClientById(id);
        return ResponseEntity.noContent().build();
    }
}