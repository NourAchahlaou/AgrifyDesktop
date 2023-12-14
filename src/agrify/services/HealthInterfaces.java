/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;

import agrify.entities.Healh;
import java.util.List;

/**
 *
 * @author Hadil sghair
 */
public interface HealthInterfaces {
    
     public void ajoutermaladie(Healh h);
     public void modifiermaladie(int id,Healh h);
     public void supprimermaladie(int id);
     public List <Healh> affichermaladie();
    

}
