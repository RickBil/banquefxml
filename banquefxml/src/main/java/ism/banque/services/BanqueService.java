package  ism.banque.services;

import java.util.List;

import  ism.banque.entities.Agence;
import  ism.banque.entities.Client;
import  ism.banque.entities.Compte;
import ism.banque.entities.User;
import  ism.banque.repositories.IAgenceRepository;
import  ism.banque.repositories.IClientRepository;
import  ism.banque.repositories.ICompteRepository;
import ism.banque.repositories.IUserRepository;

//Metier
public class BanqueService  implements IBanqueService{
    
    //Dependance 
     //Couplage Fort => Dependance sous forme de classe
     //Couplage Faible  =>  Dependance sous forme de Interface
    IAgenceRepository agenceRepository;
    ICompteRepository compteRepository;
    IClientRepository clientRepository;
    IUserRepository userRepository;

    public BanqueService(IAgenceRepository agenceRepository, ICompteRepository compteRepository,
            IClientRepository clientRepository,  IUserRepository userRepository ) {
           this.agenceRepository = agenceRepository;
           this.compteRepository = compteRepository;
           this.clientRepository = clientRepository;
           this.userRepository=userRepository;
    }

    @Override
    public List<Agence> listerAgence(){
        return agenceRepository.findAll();
    }

    @Override
    public Agence ajouterAgence(Agence agence){
       return agenceRepository.insert(agence);
    }

    @Override
    public Agence rechercherAgenceParNum(String num){
        return agenceRepository.findByNumero(num);
     }

    @Override
     public Compte creerCompte(Compte compte){
        return compteRepository.insert(compte);
     }

     @Override
     public Client creerClient(Client client){
        return clientRepository.insert(client);
     }

     @Override
     public Client rechercherClientParTel(String tel){
        return clientRepository.findByTel(tel);
     }

     @Override
      public  List<Compte> lesComptesUnClient(String tel){
        return compteRepository.findByClient(tel);
     }

   @Override
   public User seConnecter(String login, String password) {   
      return userRepository.findUserByLoginAndPassword(login, password);
   }

}
