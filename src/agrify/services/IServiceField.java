/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;

/**
 *
 * @author tbagh
 */
import java.sql.SQLException;
import java.util.List;

public interface IServiceField<T> 

    {
        void ajouter(T entity);
        void modifier(T entity);
        void supprimer(int entityId);
        T getOne(int entityId);
        void supprimerByName(String field_Nom) ;
        List<T> getAll();
        List<T> searchFieldsByName(String name) throws SQLException;
    }


