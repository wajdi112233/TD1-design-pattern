import java.util.Stack;

public class GestionnaireCommandes {
    private final Stack<commande> historique;
    
    public GestionnaireCommandes() {
        this.historique = new Stack<>();
    }
    
    public void executerCommande(commande commande) {
        commande.executer();
        historique.push(commande);
    }
    
    public void annulerDerniereCommande() {
        if (!historique.isEmpty()) {
            commande commande = historique.pop();
            commande.annuler();
            System.out.println("Commande annulée.");
        } else {
            System.out.println("Aucune commande à annuler.");
        }
    }
    
    public boolean peutAnnuler() {
        return !historique.isEmpty();
    }
    
    public int getTailleHistorique() {
        return historique.size();
    }
}
