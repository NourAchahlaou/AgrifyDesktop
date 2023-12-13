/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.services;

/**
 *
 * @author alien kami sama
 */
import agrify.entities.ValeurNutritionnelBesoinNutritionnelEntity;
import agrify.utils.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ServiceValeurNutritionnelBesoin implements IServiceValeurNutritionnelBesoin<ValeurNutritionnelBesoinNutritionnelEntity> {
    private Connection connect;
    private Database database;

    public ServiceValeurNutritionnelBesoin(Connection connection) {
        this.connect = connection;
    }

    public ServiceValeurNutritionnelBesoin(Database database) {
        this.database = database;
    }
    public ServiceValeurNutritionnelBesoin(Database database, Connection connection) {
    this.database = database;
    this.connect = connection;
}
    public void ajouter(ValeurNutritionnelBesoinNutritionnelEntity valeurNutritionnel) {
        try {
            PreparedStatement statement = connect.prepareStatement("INSERT INTO `valeurnutritionnelbesoin` (`pb`, `fb`, `adf`, `ndf`, `ms`, `eb`, `ca`, `p`, `mg`, `k`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setDouble(1, valeurNutritionnel.getPb());
            statement.setDouble(2, valeurNutritionnel.getFb());
            statement.setDouble(3, valeurNutritionnel.getAdf());
            statement.setDouble(4, valeurNutritionnel.getNdf());
            statement.setDouble(5, valeurNutritionnel.getMs());
            statement.setDouble(6, valeurNutritionnel.getEb());
            statement.setDouble(7, valeurNutritionnel.getCa());
            statement.setDouble(8, valeurNutritionnel.getP());
            statement.setDouble(9, valeurNutritionnel.getMg());
            statement.setDouble(10, valeurNutritionnel.getK());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ValeurNutritionnelBesoinNutritionnelEntity getById(int id) {
        ValeurNutritionnelBesoinNutritionnelEntity valeurNutritionnel = null;
        try {
            PreparedStatement statement = connect.prepareStatement("SELECT `idValeurNutritionnel`, `pb`, `fb`, `adf`, `ndf`, `ms`, `eb`, `ca`, `p`, `mg`, `k` FROM `valeurnutritionnelbesoin` WHERE `idValeurNutritionnel` = ?");
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                valeurNutritionnel = new ValeurNutritionnelBesoinNutritionnelEntity();
                valeurNutritionnel.setIdValeurNutritionnel(result.getInt("idValeurNutritionnel"));
                valeurNutritionnel.setPb(result.getDouble("pb"));
                valeurNutritionnel.setFb(result.getDouble("fb"));
                valeurNutritionnel.setAdf(result.getDouble("adf"));
                valeurNutritionnel.setNdf(result.getDouble("ndf"));
                valeurNutritionnel.setMs(result.getDouble("ms"));
                valeurNutritionnel.setEb(result.getDouble("eb"));
                valeurNutritionnel.setCa(result.getDouble("ca"));
                valeurNutritionnel.setP(result.getDouble("p"));
                valeurNutritionnel.setMg(result.getDouble("mg"));
                valeurNutritionnel.setK(result.getDouble("k"));
            }

            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return valeurNutritionnel;
    }
}
