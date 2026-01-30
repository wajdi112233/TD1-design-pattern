public class ApplicationGestionTaches {
    
    public static void main(String[] args) {
        GestionTaches gestionTaches = new GestionTaches();
        GestionnaireCommandes gestionnaire = new GestionnaireCommandes();
        
        System.out.println("=== Démonstration du Pattern Commande ===\n");
        
        System.out.println("--- Création de tâches ---");
        Tache tache1 = new Tache("T1", "Faire les courses");
        commande creer1 = new CreerTacheCommande(gestionTaches, tache1);
        gestionnaire.executerCommande(creer1);
        
        Tache tache2 = new Tache("T2", "Réviser le cours de Java");
        commande creer2 = new CreerTacheCommande(gestionTaches, tache2);
        gestionnaire.executerCommande(creer2);
        
        Tache tache3 = new Tache("T3", "Appeler le médecin");
        commande creer3 = new CreerTacheCommande(gestionTaches, tache3);
        gestionnaire.executerCommande(creer3);
        
        gestionTaches.afficherTaches();
        
        System.out.println("--- Modification d'une tâche ---");
        commande modifier = new ModifierTacheCommande(gestionTaches, "T2", 
                                                       "Réviser le cours de Java et faire les exercices");
        gestionnaire.executerCommande(modifier);
        gestionTaches.afficherTaches();
        
        System.out.println("--- Suppression d'une tâche ---");
        commande supprimer = new SupprimerTacheCommande(gestionTaches, "T1");
        gestionnaire.executerCommande(supprimer);
        gestionTaches.afficherTaches();
        
        System.out.println("--- Annulation de la suppression ---");
        gestionnaire.annulerDerniereCommande();
        gestionTaches.afficherTaches();
        
        System.out.println("--- Annulation de la modification ---");
        gestionnaire.annulerDerniereCommande();
        gestionTaches.afficherTaches();
        
        System.out.println("--- Annulation de la création de T3 ---");
        gestionnaire.annulerDerniereCommande();
        gestionTaches.afficherTaches();
        
        System.out.println("=== Fin de la démonstration ===");
    }
}
