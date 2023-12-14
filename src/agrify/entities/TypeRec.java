/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.entities;

/**
 *
 * @author Hadil sghair
 */
public class TypeRec {
        private int id;
    private String type;
    private String description;

    public TypeRec(int id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public TypeRec() {
    }

    public TypeRec(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TypeRec{" + "id=" + id + ", type=" + type + ", description=" + description + '}';
    }
    
    
    
}


