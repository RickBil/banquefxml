package  ism.banque.repositories;

import  ism.banque.entities.Client;

public interface IClientRepository {
    public Client insert(Client client);
    public Client findByTel(String tel);
    public Client findById(int id);
}
