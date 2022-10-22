package  ism.banque.repositories;

import java.util.List;

import  ism.banque.entities.Agence;

public interface IAgenceRepository {
    public  List<Agence>  findAll();    
    public Agence insert (Agence agence);
    public Agence findByNumero(String num);
}
