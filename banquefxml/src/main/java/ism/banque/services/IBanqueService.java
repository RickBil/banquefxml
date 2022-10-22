package  ism.banque.services;

import java.util.List;

import  ism.banque.entities.Agence;
import  ism.banque.entities.Client;
import  ism.banque.entities.Compte;
import ism.banque.entities.User;

public interface IBanqueService {
    public List<Agence> listerAgence();
    public Agence ajouterAgence(Agence agence);
    public Agence rechercherAgenceParNum(String num);
    public Compte creerCompte(Compte compte);
    public Client creerClient(Client client);
    public Client rechercherClientParTel(String tel);
    public  List<Compte> lesComptesUnClient(String tel);
    public User seConnecter(String login,String password);
}
