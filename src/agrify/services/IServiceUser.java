/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tbagh
 */
public interface IServiceUser <T>
    {
        public void ajouter(T t);
        public void modifier(T t);
        public void supprimer(int id);
        public List<T> searchUsersByName(String name) throws SQLException ;
        public T getOne(int id);  
        public List<T> getAll();
        public void supprimerByName(String name);
        public void updateUserr(T t);
        //public T getUserBest(int year) throws SQLException ;
        public boolean isPhoneExists(String phone) ;
        public boolean isUsernameExists(String username);
        public T authenticateUser(String username, String password);
        public List<T> getUserBest(int year) throws SQLException ;





    }
