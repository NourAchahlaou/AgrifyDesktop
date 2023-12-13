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

import java.time.LocalDate;

public class Presence {
    private int id_p;                
    private int user_id;             
    private LocalDate date;             
    private String presenceState;   

    public Presence() {}

    public Presence(int user_id, LocalDate date, String presenceState) {
        this.user_id = user_id;
        this.date = date;
        this.presenceState = presenceState;
    }



    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPresenceState() {
        return presenceState;
    }

    public void setPresenceState(String presenceState) {
        this.presenceState = presenceState;
    }

}
