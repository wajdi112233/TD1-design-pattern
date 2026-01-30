public class CreerTacheCommande implements commande {
    private final GestionTaches gestionTaches;
    private final Tache tache;
    
    public CreerTacheCommande(GestionTaches gestionTaches, Tache tache) {
        this.gestionTaches = gestionTaches;
        this.tache = tache;
    }
    
    @Override
    public void executer() {
        gestionTaches.ajouterTache(tache);
    }
    
    @Override
    public void annuler() {
        gestionTaches.supprimerTache(tache.getId());
    }
}
