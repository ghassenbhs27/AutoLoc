package tn.esprit.autoloc.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.domain.enums.StatutVehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(VehiculeRepository vehiculeRepository) {
        return args -> {
            if (vehiculeRepository.count() == 0) {
                Vehicule v1 = new Vehicule();
                v1.setMarque("Renault");
                v1.setModele("Clio");
                v1.setImmatriculation("123 TU 4567");
                v1.setAnnee(2022);
                v1.setStatut(StatutVehicule.DISPONIBLE);

                Vehicule v2 = new Vehicule();
                v2.setMarque("Peugeot");
                v2.setModele("208");
                v2.setImmatriculation("789 TU 1234");
                v2.setAnnee(2023);
                v2.setStatut(StatutVehicule.DISPONIBLE);

                Vehicule v3 = new Vehicule();
                v3.setMarque("Volkswagen");
                v3.setModele("Golf");
                v3.setImmatriculation("456 TU 7890");
                v3.setAnnee(2021);
                v3.setStatut(StatutVehicule.LOUE);

                vehiculeRepository.save(v1);
                vehiculeRepository.save(v2);
                vehiculeRepository.save(v3);

                System.out.println("✅ 3 véhicules de démonstration insérés en base.");
            }
        };
    }
}
