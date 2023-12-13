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
    private Date preparationVêlage;
    private Date vêlagePrévu;
    private Date dateAvertissement;

    public AnimauxEnGestationEntity(long id, String espece, Date preparationVêlage, Date vêlagePrévu, Date dateAvertissement) {
        this.id = id;
        this.espece = espece;
        this.preparationVêlage = preparationVêlage;
        this.vêlagePrévu = vêlagePrévu;
        this.dateAvertissement = dateAvertissement;
    }

    public AnimauxEnGestationEntity(String espece, Date preparationVêlage, Date vêlagePrévu, Date dateAvertissement) {
        this.espece = espece;
        this.preparationVêlage = preparationVêlage;
        this.vêlagePrévu = vêlagePrévu;
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

    public Date getPreparationVêlage() {
        return preparationVêlage;
    }

    public void setPreparationVêlage(Date preparationVêlage) {
        this.preparationVêlage = preparationVêlage;
    }

    public Date getVêlagePrévu() {
        return vêlagePrévu;
    }

    public void setVêlagePrévu(Date vêlagePrévu) {
        this.vêlagePrévu = vêlagePrévu;
    }

    public Date getDateAvertissement() {
        return dateAvertissement;
    }

    public void setDateAvertissement(Date dateAvertissement) {
        this.dateAvertissement = dateAvertissement;
    }
}
