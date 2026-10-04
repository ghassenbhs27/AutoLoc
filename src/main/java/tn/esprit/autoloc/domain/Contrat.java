package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    private LocalDate dateSignature;
    private Double montantTotal;
    private Boolean valide;

    @OneToMany(mappedBy = "contrat", fetch = FetchType.LAZY)
    private List<Paiement> paiements = new ArrayList<>();

    @OneToOne(mappedBy = "contrat", fetch = FetchType.LAZY)
    private Reservation reservation;
}
