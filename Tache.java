public class Tache {
    private final String id;
    private String description;
    private boolean terminee;
    
    public Tache(String id, String description) {
        this.id = id;
        this.description = description;
        this.terminee = false;
    }
    
    public String getId() {
        return id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isTerminee() {
        return terminee;
    }
    
    public void setTerminee(boolean terminee) {
        this.terminee = terminee;
    }
    
    @Override
    public String toString() {
        return "Tache[" + id + "] " + description + " - " + 
               (terminee ? "Terminée" : "En cours");
    }
}
