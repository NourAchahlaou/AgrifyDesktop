/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.entities;

/**
 *
 * @author tbagh
 */

public class User {
    private int user_id;
    private String user_nom;
    private String user_prenom;
    private String user_email;
    private String user_telephone;
    private String user_role;
    private String user_genre;
    private int user_nbrabscence;
    private String username;
    private String password;

    public User() {}

    public User(String user_nom, String user_prenom, String user_email, String user_telephone, String user_role, String user_genre, int user_nbrabscence, String username, String password) {
        this.user_nom = user_nom;
        this.user_prenom = user_prenom;
        this.user_email = user_email;
        this.user_telephone = user_telephone;
        this.user_role = user_role;
        this.user_genre = user_genre;
        this.user_nbrabscence = user_nbrabscence;
        this.username = username;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_nom() {
        return user_nom;
    }

    public void setUser_nom(String user_nom) {
        this.user_nom = user_nom;
    }

    public String getUser_prenom() {
        return user_prenom;
    }

    public void setUser_prenom(String user_prenom) {
        this.user_prenom = user_prenom;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_telephone() {
        return user_telephone;
    }

    public void setUser_telephone(String user_telephone) {
        this.user_telephone = user_telephone;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getUser_genre() {
        return user_genre;
    }

    public void setUser_genre(String user_genre) {
        this.user_genre = user_genre;
    }

    public int getUser_nbrabscence() {
        return user_nbrabscence;
    }

    public void setUser_nbrabscence(int user_nbrabscence) {
        this.user_nbrabscence = user_nbrabscence;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
