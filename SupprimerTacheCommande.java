public class SupprimerTacheCommande implements commande {
    private final GestionTaches gestionTaches;
    private final String idTache;
    private Tache tacheSupprimee;
    
    public SupprimerTacheCommande(GestionTaches gestionTaches, String idTache) {
        this.gestionTaches = gestionTaches;
        this.idTache = idTache;
    }
    
    @Override
    public void executer() {
        tacheSupprimee = gestionTaches.getTache(idTache);
        if (tacheSupprimee != null) {
            gestionTaches.supprimerTache(idTache);
        }
    }
    
    @Override
    public void annuler() {
        if (tacheSupprimee != null) {
            gestionTaches.ajouterTache(tacheSupprimee);
        }
    }
}
