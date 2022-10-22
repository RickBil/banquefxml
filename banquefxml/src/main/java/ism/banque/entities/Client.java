package  ism.banque.entities;

public class Client extends User{
    private String tel;

  

    public Client(int id, Role role, String login, String password, String nomComplet, String tel) {
        super(id, role, login, password, nomComplet);
        this.tel = tel;
    }

    public Client(String nomComplet, String tel) {
        super(nomComplet);
        this.tel = tel;
    }

    public Client(String login, String password, String nomComplet, String tel) {
        super(login, password, nomComplet);
        this.tel = tel;
        role=Role.Client;
    }

    public Client() {
        role=Role.Client;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
