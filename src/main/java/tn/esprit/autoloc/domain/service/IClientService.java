package tn.esprit.autoloc.domain.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;

public interface IClientService {
    Client ajouterClient(Client client);
    Client modifierClient(Client client);
    List<Client> consulterClients();
    Client consulterClientById(Long idClient);
    void supprimerClientById(Long idClient);
}
