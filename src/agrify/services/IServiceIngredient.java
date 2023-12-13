/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;

import java.util.List;

/**
 *
 * @author alien kami sama
 */
public interface IServiceIngredient<T> {
    public void ajouter(T t);
    public void update(T t);
    public void supprimer(int id);
   
}
