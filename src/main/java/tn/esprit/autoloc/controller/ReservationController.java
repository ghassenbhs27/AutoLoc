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
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final IReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> ajouter(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.ajouterReservation(reservation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> modifier(@PathVariable Long id, @RequestBody Reservation reservation) {
        if (reservationService.consulterReservationById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        reservation.setIdReservation(id);
        return ResponseEntity.ok(reservationService.modifierReservation(reservation));
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> consulterTous() {
        return ResponseEntity.ok(reservationService.consulterReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> consulterParId(@PathVariable Long id) {
        Reservation reservation = reservationService.consulterReservationById(id);
        return reservation == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (reservationService.consulterReservationById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        reservationService.supprimerReservationById(id);
        return ResponseEntity.noContent().build();
    }
}