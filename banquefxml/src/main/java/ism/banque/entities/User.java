package  ism.banque.entities;

public class User {

    protected int id;
    protected Role role;
    protected String login;
    protected String password;
    protected String nomComplet;

    //ManyToOne  =>  Gestionnaire
    protected Agence agence;
    
    public Agence getAgence() {
        return agence;
    }


    public void setAgence(Agence agence) {
        this.agence = agence;
    }


    public User(int id, Role role, String login, String password, String nomComplet) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
        this.nomComplet = nomComplet;
    }


    public User(String nomComplet) {
        this.nomComplet = nomComplet;
        role=Role.Gestionnaire;
        
    }


    public User(String login, String password, String nomComplet) {
        this.login = login;
        this.password = password;
        this.nomComplet = nomComplet;
        role=Role.Gestionnaire;
       
    }

    

    public User() {
        role=Role.Gestionnaire;
       
    }

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNomComplet() {
        return nomComplet;
    }
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
}
