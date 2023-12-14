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


public class AnimauxEnGestationEntity {
    private long id;
    private String espece;
    private Date preparationVelage;
    private Date velagePrevu;
    private Date dateAvertissement;

    public AnimauxEnGestationEntity(long id, String espece, Date preparationVelage, Date velagePrevu, Date dateAvertissement) {
        this.id = id;
        this.espece = espece;
        this.preparationVelage = preparationVelage;
        this.velagePrevu = velagePrevu;
        this.dateAvertissement = dateAvertissement;
    }

    public AnimauxEnGestationEntity(String espece, Date preparationVelage, Date velagePrevu, Date dateAvertissement) {
        this.espece = espece;
        this.preparationVelage = preparationVelage;
        this.velagePrevu = velagePrevu;
        this.dateAvertissement = dateAvertissement;    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public Date getPreparationVelage() {
        return preparationVelage;
    }

    public void setPreparationVelage(Date preparationVelage) {
        this.preparationVelage = preparationVelage;
    }

    public Date getVelagePrevu() {
        return velagePrevu;
    }

    public void setVelagePrevu(Date velagePrevu) {
        this.velagePrevu = velagePrevu;
    }

    public Date getDateAvertissement() {
        return dateAvertissement;
    }

    public void setDateAvertissement(Date dateAvertissement) {
        this.dateAvertissement = dateAvertissement;
    }
}
