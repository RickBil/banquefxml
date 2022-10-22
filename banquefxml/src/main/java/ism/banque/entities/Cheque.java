package  ism.banque.entities;

public class Cheque extends Compte {
    private double frais;

    public Cheque(int id, String numero, double solde, double frais) {
        super(id, numero, solde);
        this.frais = frais;     typeCompte=TypeCompte.Cheque;
        type=TypeCompte.Cheque.name();
    }

    public Cheque(double solde, double frais) {
        super(solde);
        this.frais = frais;
        typeCompte=TypeCompte.Cheque;
        type=TypeCompte.Cheque.name();
    }

    public Cheque() {
        typeCompte=TypeCompte.Cheque;  type=TypeCompte.Cheque.name();
    }

    public double getFrais() {
        return frais;
    }

    public void setFrais(double frais) {
        this.frais = frais;
    }

    @Override
    public String toString() {
        return "Cheque"+ super.toString() +" frais=" + frais + "]";
    }
}
