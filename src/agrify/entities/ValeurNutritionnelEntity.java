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
public class ValeurNutritionnelEntity {
    private int idValeurNutritionnel;
    private double pb;  // Protein
    private double fb;  // Fiber
    private double adf; // Acid Detergent Fiber
    private double ndf; // Neutral Detergent Fiber
    private double ms;  // Moisture
    private double eb;  // Ether Extract (Fat)
    private double ca;  // Calcium
    private double p;   // Phosphorus
    private double mg;  // Magnesium
    private double k;   // Potassium

    public ValeurNutritionnelEntity() {
        // Default constructor
    }

    public ValeurNutritionnelEntity(double pb, double fb, double adf, double ndf, double ms, double eb, double ca, double p, double mg, double k) {
        this.pb = pb;
        this.fb = fb;
        this.adf = adf;
        this.ndf = ndf;
        this.ms = ms;
        this.eb = eb;
        this.ca = ca;
        this.p = p;
        this.mg = mg;
        this.k = k;
    }
    
@Override
public String toString() {
    return "ValeurNutritionnelEntity{" +
            "idValeurNutritionnel=" + idValeurNutritionnel +
            ", pb=" + pb +
            ", fb=" + fb +
            ", adf=" + adf +
            ", ndf=" + ndf +
            ", ms=" + ms +
            ", eb=" + eb +
            ", ca=" + ca +
            ", p=" + p +
            ", mg=" + mg +
            ", k=" + k +
            '}';
}
    public int getIdValeurNutritionnel() {
    return idValeurNutritionnel;
}

public void setIdValeurNutritionnel(int idValeurNutritionnel) {
    this.idValeurNutritionnel = idValeurNutritionnel;
}

    public double getPb() {
        return pb;
    }

    public void setPb(double pb) {
        this.pb = pb;
    }

    public double getFb() {
        return fb;
    }

    public void setFb(double fb) {
        this.fb = fb;
    }

    public double getAdf() {
        return adf;
    }

    public void setAdf(double adf) {
        this.adf = adf;
    }

    public double getNdf() {
        return ndf;
    }

    public void setNdf(double ndf) {
        this.ndf = ndf;
    }

    public double getMs() {
        return ms;
    }

    public void setMs(double ms) {
        this.ms = ms;
    }

    public double getEb() {
        return eb;
    }

    public void setEb(double eb) {
        this.eb = eb;
    }

    public double getCa() {
        return ca;
    }

    public void setCa(double ca) {
        this.ca = ca;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public double getMg() {
        return mg;
    }

    public void setMg(double mg) {
        this.mg = mg;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }


}
