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
public class Animal {

    private int id;
    private String espece;
    private String sexe;
    private double poids;
    private int age;
    private String unit_animal;

        // Constructors
    public Animal(int id, String espece, String sexe, double poids, int age, String unit_animal) {
        this.id = id;
        this.espece = espece;
        this.sexe = sexe;
        this.poids = poids;
        this.age = age;
        this.unit_animal = unit_animal;
    }

        // Constructors
    public Animal( String espece, String sexe, double poids, int age, String unit_animal) {
        this.espece = espece;
        this.sexe = sexe;
        this.poids = poids;
        this.age = age;
        this.unit_animal = unit_animal;
    }


    public Animal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        // Constructors
    public Animal(int id, String espece, double poids, String unit_animal) {
        this.id = id;
        this.espece = espece;
        this.poids = poids;
        this.unit_animal = unit_animal;
    }


        // Constructors


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int ageAnimal) {
        this.age = age;
    }

    public String getUnit_animal() {
        return unit_animal;
    }

    public void setUnit_animal(String unit_animal) {
        this.unit_animal = unit_animal;
    }

}
