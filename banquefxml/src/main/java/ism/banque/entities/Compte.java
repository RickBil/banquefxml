package  ism.banque.entities;


abstract public class Compte {
    
     private static int nbre;
     protected int id; 
     protected String numero; 
     protected double solde; 
     protected TypeCompte typeCompte;
     protected String type;
    //ManyToOne
     protected Client client;
   //ManyToOne
     protected Agence agence;
     
    public Compte(int id, String numero, double solde) {
        this.id = id;
        this.numero = numero;
        this.solde = solde;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Compte(double solde) {
        nbre++;
        id=nbre;
        numero="CPT_"+id;
        this.solde = solde;
    }

    public Compte() {
        nbre++;
        id=nbre;
        numero="CPT_"+id;

    }

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public double getSolde() {
        return solde;
    }
    public void setSolde(double solde) {
        this.solde = solde;
    }
    public TypeCompte getTypeCompte() {
        return typeCompte;
    }
    public void setTypeCompte(TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
    }

    @Override
    public String toString() {
        return " [id=" + id + ", numero=" + numero + ", solde=" + solde ;
    }

    public static int getNbre() {
        return nbre;
    }

    public String getType() {
        return type;
    } 


}
