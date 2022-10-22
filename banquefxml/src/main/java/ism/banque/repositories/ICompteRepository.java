package  ism.banque.repositories;

import java.util.List;

import  ism.banque.entities.Compte;

public interface ICompteRepository {
    public Compte insert(Compte compte);
    public  List<Compte> findByClient(String tel);
}
