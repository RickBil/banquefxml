package ism.banque.core;

import ism.banque.repositories.IAgenceRepository;
import ism.banque.repositories.IClientRepository;
import ism.banque.repositories.ICompteRepository;
import ism.banque.repositories.IUserRepository;
import ism.banque.repositories.bd.AgenceRepository;
import ism.banque.repositories.bd.ClientRepository;
import ism.banque.repositories.bd.CompteRepository;
import ism.banque.repositories.bd.UserRepository;
import ism.banque.services.BanqueService;
import ism.banque.services.IBanqueService;

public class Fabrique {
    

    public  static IBanqueService getService(){
         IAgenceRepository agenceRepository=new AgenceRepository();
         IClientRepository clientRepository=new ClientRepository();
         ICompteRepository compteRepository =new CompteRepository(clientRepository);
         IUserRepository userRepository =new UserRepository();
        return new BanqueService(agenceRepository,compteRepository,clientRepository,userRepository);
    }
}
