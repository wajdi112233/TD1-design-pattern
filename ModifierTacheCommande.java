public class ModifierTacheCommande implements commande {
    private GestionTaches gestionTaches;
    private String idTache;
    private final String nouvelleDescription;
    private String ancienneDescription;
    
    public ModifierTacheCommande(GestionTaches gestionTaches, String idTache, 
                                  String nouvelleDescription) {
        this.gestionTaches = gestionTaches;
        this.idTache = idTache;
        this.nouvelleDescription = nouvelleDescription;
    }

    public ModifierTacheCommande(String nouvelleDescription) {
        this.nouvelleDescription = nouvelleDescription;
    }
    
    @Override
    public void executer() {
        Tache tache = gestionTaches.getTache(idTache);
        if (tache != null) {
            ancienneDescription = tache.getDescription();
            gestionTaches.modifierTache(idTache, nouvelleDescription);
        }
    }
    
    @Override
    public void annuler() {
        if (ancienneDescription != null) {
            gestionTaches.modifierTache(idTache, ancienneDescription);
        }
    }
}
