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
public class Ration {

    private int idRation;
    private String especeRation;
    private String statutRation;
    private String sexeRation;
    private double poidsMinRation;
    private double poidsMaxRation;
    private String buteProductionRation;

    // Constructor with no parameters
    public Ration() {
    }

    // Constructor with all fields
    public Ration(int idRation, String especeRation, String statutRation, String sexeRation, double poidsMinRation, double poidsMaxRation, String buteProductionRation) {
        this.idRation = idRation;
        this.especeRation = especeRation;
        this.statutRation = statutRation;
        this.sexeRation = sexeRation;
        this.poidsMinRation = poidsMinRation;
        this.poidsMaxRation = poidsMaxRation;
        this.buteProductionRation = buteProductionRation;

    }

    public Ration(String especeRation, String statutRation, String sexeRation, double poidsMinRation, double poidsMaxRation, String buteProductionRation) {
        this.especeRation = especeRation;
        this.statutRation = statutRation;
        this.sexeRation = sexeRation;
        this.poidsMinRation = poidsMinRation;
        this.poidsMaxRation = poidsMaxRation;
        this.buteProductionRation = buteProductionRation;
    }

    // Getters and setters for all fields
    public int getIdRation() {
        return idRation;
    }

    public void setIdRation(int idRation) {
        this.idRation = idRation;
    }

    public String getEspeceRation() {
        return especeRation;
    }

    public void setEspeceRation(String especeRation) {
        this.especeRation = especeRation;
    }

    public String getStatutRation() {
        return statutRation;
    }

    public void setStatutRation(String statutRation) {
        this.statutRation = statutRation;
    }

    public String getSexeRation() {
        return sexeRation;
    }

    public void setSexeRation(String sexeRation) {
        this.sexeRation = sexeRation;
    }

    public double getPoidsMinRation() {
        return poidsMinRation;
    }

    public void setPoidsMinRation(double poidsMinRation) {
        this.poidsMinRation = poidsMinRation;
    }

    public double getPoidsMaxRation() {
        return poidsMaxRation;
    }

    public void setPoidsMaxRation(double poidsMaxRation) {
        this.poidsMaxRation = poidsMaxRation;
    }

    public String getButeProductionRation() {
        return buteProductionRation;
    }

    public void setButeProductionRation(String buteProductionRation) {
        this.buteProductionRation = buteProductionRation;
    }
}
