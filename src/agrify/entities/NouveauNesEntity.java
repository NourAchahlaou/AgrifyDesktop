/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.entities;

import java.sql.Date;

/**
 *
 * @author alien kami sama
 */
public class NouveauNesEntity {
    
    private long id;
    private String sexe;
    private Date dateNaissance;
    private String espece;
    private double poids;
    private String etatSante;

    // Constructors, getters, and setters go here

    // Constructor
    public NouveauNesEntity(long id, String sexe, Date dateNaissance, String espece, double poids, String etatSante) {
        this.id = id;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.espece = espece;
        this.poids = poids;
        this.etatSante = etatSante;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public String getEtatSante() {
        return etatSante;
    }

    public void setEtatSante(String etatSante) {
        this.etatSante = etatSante;
    }


}
