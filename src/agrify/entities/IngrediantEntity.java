/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.entities;

/**
 *
 * @author alien kami sama
 */
public class IngrediantEntity {
   
    private int idIngredient;
    private String nameIngredient;
    private double itemQuantityIngredient;
    private String unitIngredient;
    private double costIngredient;
    private String loadedByIngredient;
    private String descriptionIngredient;
    private String typeIngredient;
    private String nutrimentPrincipalIngredient;

    
     public IngrediantEntity(){}
    public IngrediantEntity(String nom, String type, String description, String prix, String quantite, String udm, String source,String nutrimentPrincipal) {
        this.nameIngredient = nom;
        // Set other attributes as needed
        this.typeIngredient = type;
        this.descriptionIngredient = description;
        this.costIngredient = Double.parseDouble(prix);
        this.itemQuantityIngredient = Double.parseDouble(quantite);
        this.unitIngredient = udm;
        this.loadedByIngredient = source;
        this.nutrimentPrincipalIngredient = nutrimentPrincipal;
    }
    public IngrediantEntity(String nom, String prix, String source,String nutrimentPrincipal) {
        this.nameIngredient = nom;
        // Set other attributes as needed
        
        this.costIngredient = Double.parseDouble(prix);
        
        this.loadedByIngredient = source;
        this.nutrimentPrincipalIngredient = nutrimentPrincipal;
    }
    
    

    // Constructors (you can create constructors as needed)

    // Getter and Setter methods for each attribute

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getNameIngredient() {
        return nameIngredient;
    }

    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
    }

    public double getItemQuantityIngredient() {
        return itemQuantityIngredient;
    }

    public void setItemQuantityIngredient(double itemQuantityIngredient) {
        this.itemQuantityIngredient = itemQuantityIngredient;
    }

    public String getUnitIngredient() {
        return unitIngredient;
    }

    public void setUnitIngredient(String unitIngredient) {
        this.unitIngredient = unitIngredient;
    }

    public double getCostIngredient() {
        return costIngredient;
    }

    public void setCostIngredient(double costIngredient) {
        this.costIngredient = costIngredient;
    }

    public String getLoadedByIngredient() {
        return loadedByIngredient;
    }

    public void setLoadedByIngredient(String loadedByIngredient) {
        this.loadedByIngredient = loadedByIngredient;
    }

    public String getDescriptionIngredient() {
        return descriptionIngredient;
    }

    public void setDescriptionIngredient(String descriptionIngredient) {
        this.descriptionIngredient = descriptionIngredient;
    }

    public String getTypeIngredient() {
        return typeIngredient;
    }

    public void setTypeIngredient(String typeIngredient) {
        this.typeIngredient = typeIngredient;
    }
    public String getNutrimentPrincipalIngredient() {
    return nutrimentPrincipalIngredient;
}

public void setNutrimentPrincipalIngredient(String nutrimentPrincipalIngredient) {
    this.nutrimentPrincipalIngredient = nutrimentPrincipalIngredient;
}



}
