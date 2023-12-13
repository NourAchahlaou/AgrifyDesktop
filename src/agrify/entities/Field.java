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

public class Field {
    private int field_Id;
    private String field_Nom;
    private String field_type;
    private double field_Superficie;
    private int Field_quantity;
    
    public Field() {
}


       public Field(String field_Nom, String field_type, double field_Superficie, int Field_quantity) {
        this.field_Nom = field_Nom;
        this.field_type = field_type;
        this.field_Superficie = field_Superficie;
        this.Field_quantity = Field_quantity;
    }
       
       
    public Field(int field_Id, String field_Nom, String field_type, double field_Superficie, int Field_quantity) {
        this.field_Id = field_Id;
        this.field_Nom = field_Nom;
        this.field_type = field_type;
        this.field_Superficie = field_Superficie;
        this.Field_quantity = Field_quantity;
    }

    // Getters et Setters
    public int getField_Id() {
        return field_Id;
    }

    public void setField_Id(int field_Id) {
        this.field_Id = field_Id;
    }

    public String getField_Nom() {
        return field_Nom;
    }

    public void setField_Nom(String field_Nom) {
        this.field_Nom = field_Nom;
    }

    public String getField_type() {
        return field_type;
    }

    public void setField_type(String field_type) {
        this.field_type = field_type;
    }

    public double getField_Superficie() {
        return field_Superficie;
    }

    public void setField_Superficie(double field_Superficie) {
        this.field_Superficie = field_Superficie;
    }

    public int getField_quantity() {
        return Field_quantity;
    }

    public void setField_quantity(int Field_quantity) {
        this.Field_quantity = Field_quantity;
    }

    @Override
    public String toString() {
        return "Field{" +
                "field_Id=" + field_Id +
                ", field_Nom='" + field_Nom + '\'' +
                ", field_type='" + field_type + '\'' +
                ", field_Superficie=" + field_Superficie +
                ", Field_quantity=" + Field_quantity +
                '}';
    }
}

