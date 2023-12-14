
package agrify.services;


import agrify.entities.Reclamation;
import java.util.List;
import java.util.Map;


     public interface Recinterfaces {
     public void ajouterreclamation(Reclamation rec);
     public void modifierreclamation(int id,Reclamation rec);
     public void supprimerreclamation(int id);
     public List <Reclamation> afficherreclamation();
     public Reclamation rechercheReclamationParId(int id);
 
}
