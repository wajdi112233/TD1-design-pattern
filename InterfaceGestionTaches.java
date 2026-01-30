import java.awt.*;
import javax.swing.*;

public class InterfaceGestionTaches extends JFrame {
    private final GestionTaches gestionTaches;
    private final GestionnaireCommandes gestionnaire;
    private JTextArea affichageTaches;
    private JTextField champId;
    private JTextField champDescription;
    
    public InterfaceGestionTaches() {
        gestionTaches = new GestionTaches();
        gestionnaire = new GestionnaireCommandes();
        
        setTitle("Gestion de Tâches - Pattern Commande");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
        rafraichirAffichage();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        JPanel panelSaisie = new JPanel(new GridLayout(3, 2, 5, 5));
        panelSaisie.setBorder(BorderFactory.createTitledBorder("Saisie"));
        
        panelSaisie.add(new JLabel("ID de la tâche:"));
        champId = new JTextField();
        panelSaisie.add(champId);
        
        panelSaisie.add(new JLabel("Description:"));
        champDescription = new JTextField();
        panelSaisie.add(champDescription);
        
        add(panelSaisie, BorderLayout.NORTH);
        
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createTitledBorder("Liste des tâches"));
        
        affichageTaches = new JTextArea();
        affichageTaches.setEditable(false);
        affichageTaches.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(affichageTaches);
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        
        add(panelCentral, BorderLayout.CENTER);
        
        JPanel panelBoutons = new JPanel(new GridLayout(2, 2, 10, 10));
        panelBoutons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton btnCreer = new JButton("Créer Tâche");
        btnCreer.setBackground(new Color(76, 175, 80));
        btnCreer.setForeground(Color.WHITE);
        btnCreer.setFocusPainted(false);
        btnCreer.addActionListener(e -> creerTache());
        panelBoutons.add(btnCreer);
        
        JButton btnModifier = new JButton("Modifier Tâche");
        btnModifier.setBackground(new Color(33, 150, 243));
        btnModifier.setForeground(Color.WHITE);
        btnModifier.setFocusPainted(false);
        btnModifier.addActionListener(e -> modifierTache());
        panelBoutons.add(btnModifier);
        
        JButton btnSupprimer = new JButton("Supprimer Tâche");
        btnSupprimer.setBackground(new Color(244, 67, 54));
        btnSupprimer.setForeground(Color.WHITE);
        btnSupprimer.setFocusPainted(false);
        btnSupprimer.addActionListener(e -> supprimerTache());
        panelBoutons.add(btnSupprimer);
        
        JButton btnAnnuler = new JButton("Annuler Dernière Commande");
        btnAnnuler.setBackground(new Color(255, 152, 0));
        btnAnnuler.setForeground(Color.WHITE);
        btnAnnuler.setFocusPainted(false);
        btnAnnuler.addActionListener(e -> annulerCommande());
        panelBoutons.add(btnAnnuler);
        
        add(panelBoutons, BorderLayout.SOUTH);
    }
    
    private void creerTache() {
        String id = champId.getText().trim();
        String description = champDescription.getText().trim();
        
        if (id.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez remplir l'ID et la description!", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Tache tache = new Tache(id, description);
        commande commande = new CreerTacheCommande(gestionTaches, tache);
        gestionnaire.executerCommande(commande);
        
        champId.setText("");
        champDescription.setText("");
        rafraichirAffichage();
        
        JOptionPane.showMessageDialog(this, 
            "Tâche créée avec succès!", 
            "Succès", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void modifierTache() {
        String id = champId.getText().trim();
        String nouvelleDescription = champDescription.getText().trim();
        
        if (id.isEmpty() || nouvelleDescription.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez remplir l'ID et la nouvelle description!", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (gestionTaches.getTache(id) == null) {
            JOptionPane.showMessageDialog(this, 
                "Tâche non trouvée avec l'ID: " + id, 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        commande commande = new ModifierTacheCommande(gestionTaches, id, nouvelleDescription);
        gestionnaire.executerCommande(commande);
        
        champId.setText("");
        champDescription.setText("");
        rafraichirAffichage();
        
        JOptionPane.showMessageDialog(this, 
            "Tâche modifiée avec succès!", 
            "Succès", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void supprimerTache() {
        String id = champId.getText().trim();
        
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez remplir l'ID de la tâche à supprimer!", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (gestionTaches.getTache(id) == null) {
            JOptionPane.showMessageDialog(this, 
                "Tâche non trouvée avec l'ID: " + id, 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirmation = JOptionPane.showConfirmDialog(this,
            "Êtes-vous sûr de vouloir supprimer cette tâche?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION);
            
        if (confirmation == JOptionPane.YES_OPTION) {
            commande commande = new SupprimerTacheCommande(gestionTaches, id);
            gestionnaire.executerCommande(commande);
            
            champId.setText("");
            champDescription.setText("");
            rafraichirAffichage();
            
            JOptionPane.showMessageDialog(this, 
                "Tâche supprimée avec succès!", 
                "Succès", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void annulerCommande() {
        if (!gestionnaire.peutAnnuler()) {
            JOptionPane.showMessageDialog(this, 
                "Aucune commande à annuler!", 
                "Information", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        gestionnaire.annulerDerniereCommande();
        rafraichirAffichage();
        
        JOptionPane.showMessageDialog(this, 
            "Dernière commande annulée!", 
            "Succès", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void rafraichirAffichage() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════════════════\n");
        sb.append("                   LISTE DES TÂCHES\n");
        sb.append("═══════════════════════════════════════════════════════\n\n");
        
        if (gestionTaches.getToutesLesTaches().isEmpty()) {
            sb.append("  Aucune tâche enregistrée.\n");
        } else {
            for (Tache tache : gestionTaches.getToutesLesTaches()) {
                sb.append("  • [").append(tache.getId()).append("] ")
                  .append(tache.getDescription()).append("\n")
                  .append("    Statut: ").append(tache.isTerminee() ? "✓ Terminée" : "○ En cours")
                  .append("\n\n");
            }
        }
        
        sb.append("═══════════════════════════════════════════════════════\n");
        sb.append("Commandes dans l'historique: ").append(gestionnaire.getTailleHistorique());
        
        affichageTaches.setText(sb.toString());
        affichageTaches.setCaretPosition(0);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceGestionTaches frame = new InterfaceGestionTaches();
            frame.setVisible(true);
        });
    }
}
