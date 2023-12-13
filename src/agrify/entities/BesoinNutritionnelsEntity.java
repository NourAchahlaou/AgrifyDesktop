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
public class BesoinNutritionnelsEntity {
    private int idBesoinNutritionnel;
    private String especeBesoinNutritionnel;
    private String statutProductionBesoinNutritionnel;
    private String sexeBesoinNutritionnel;
    private double poidsMinBesoinNutritionnel;
    private double poidsMaxBesoinNutritionnel;
    private String buteProductionBesoinNutritionnel;

    // Default constructor
    public BesoinNutritionnelsEntity() {
    }

    public BesoinNutritionnelsEntity(String especeBesoinNutritionnel, String statutProductionBesoinNutritionnel, String sexeBesoinNutritionnel, double poidsMinBesoinNutritionnel, double poidsMaxBesoinNutritionnel, String buteProductionBesoinNutritionnel) {
        this.especeBesoinNutritionnel = especeBesoinNutritionnel;
        this.statutProductionBesoinNutritionnel = statutProductionBesoinNutritionnel;
        this.sexeBesoinNutritionnel = sexeBesoinNutritionnel;
        this.poidsMinBesoinNutritionnel = poidsMinBesoinNutritionnel;
        this.poidsMaxBesoinNutritionnel = poidsMaxBesoinNutritionnel;
        this.buteProductionBesoinNutritionnel = buteProductionBesoinNutritionnel;
    }
    public String getEspeceBesoinNutritionnel() {
        return especeBesoinNutritionnel;
    }

    public void setEspeceBesoinNutritionnel(String especeBesoinNutritionnel) {
        this.especeBesoinNutritionnel = especeBesoinNutritionnel;
    }
    
    public int getIdBesoinNutritionnel() {
        return idBesoinNutritionnel;
    }

    public void setIdBesoinNutritionnel(int idBesoinNutritionnel) {
        this.idBesoinNutritionnel = idBesoinNutritionnel;
    }

    public String getStatutProductionBesoinNutritionnel() {
        return statutProductionBesoinNutritionnel;
    }

    public void setStatutProductionBesoinNutritionnel(String statutProductionBesoinNutritionnel) {
        this.statutProductionBesoinNutritionnel = statutProductionBesoinNutritionnel;
    }

    public String getSexeBesoinNutritionnel() {
        return sexeBesoinNutritionnel;
    }

    public void setSexeBesoinNutritionnel(String sexeBesoinNutritionnel) {
        this.sexeBesoinNutritionnel = sexeBesoinNutritionnel;
    }

    public double getPoidsMinBesoinNutritionnel() {
        return poidsMinBesoinNutritionnel;
    }

    public void setPoidsMinBesoinNutritionnel(double poidsMinBesoinNutritionnel) {
        this.poidsMinBesoinNutritionnel = poidsMinBesoinNutritionnel;
    }

    public double getPoidsMaxBesoinNutritionnel() {
        return poidsMaxBesoinNutritionnel;
    }

    public void setPoidsMaxBesoinNutritionnel(double poidsMaxBesoinNutritionnel) {
        this.poidsMaxBesoinNutritionnel = poidsMaxBesoinNutritionnel;
    }

    public String getButeProductionBesoinNutritionnel() {
        return buteProductionBesoinNutritionnel;
    }

    public void setButeProductionBesoinNutritionnel(String buteProductionBesoinNutritionnel) {
        this.buteProductionBesoinNutritionnel = buteProductionBesoinNutritionnel;
    }
}
