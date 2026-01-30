import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GestionTaches {
    private final Map<String, Tache> taches;
    
    public GestionTaches() {
        this.taches = new HashMap<>();
    }
    
    public void ajouterTache(Tache tache) {
        taches.put(tache.getId(), tache);
        System.out.println("Tâche ajoutée : " + tache);
    }
    
    public void supprimerTache(String id) {
        Tache tache = taches.remove(id);
        if (tache != null) {
            System.out.println("Tâche supprimée : " + tache);
        }
    }
    
    public void modifierTache(String id, String nouvelleDescription) {
        Tache tache = taches.get(id);
        if (tache != null) {
            tache.setDescription(nouvelleDescription);
            System.out.println("Tâche modifiée : " + tache);
        }
    }
    
    public Tache getTache(String id) {
        return taches.get(id);
    }
    
    public Collection<Tache> getToutesLesTaches() {
        return taches.values();
    }
    
    public void afficherTaches() {
        System.out.println("\n=== Liste des tâches ===");
        if (taches.isEmpty()) {
            System.out.println("Aucune tâche.");
        } else {
            for (Tache tache : taches.values()) {
                System.out.println(tache);
            }
        }
        System.out.println("========================\n");
    }
}
