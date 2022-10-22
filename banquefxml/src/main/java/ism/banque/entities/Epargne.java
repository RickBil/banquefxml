package  ism.banque.entities;

public class Epargne extends Compte {
    private double taux;
    
    public Epargne(int id, String numero, double solde, double taux) {
        super(id, numero, solde);
        this.taux = taux;
        typeCompte=TypeCompte.Cheque;
        type=TypeCompte.Cheque.name();
    }



    public Epargne(double solde, double taux) {
        super(solde);
        this.taux = taux; 
        typeCompte=TypeCompte.Epargne;
        type=TypeCompte.Epargne.name();
    }



    public Epargne() {
        super();
        typeCompte=TypeCompte.Epargne;
        type=TypeCompte.Epargne.name();
    }

    

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }



    @Override
    public String toString() {
        return "Epargne "+super.toString()+" Taux=" + taux + "]";
    }
}
