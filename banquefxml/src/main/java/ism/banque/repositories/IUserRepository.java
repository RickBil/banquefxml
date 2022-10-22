package ism.banque.repositories;

import ism.banque.entities.User;

public interface  IUserRepository {
    
    public User  findUserByLoginAndPassword(String login,String password);
}
