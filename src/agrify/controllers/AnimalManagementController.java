/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agrify.controllers;

import agrify.entities.Animal;
import agrify.entities.AnimauxEnGestationEntity;
import agrify.entities.BesoinNutritionnelsEntity;
import agrify.entities.IngrediantEntity;
import agrify.entities.Ration;
import agrify.entities.ValeurNutritionnelBesoinNutritionnelEntity;
import agrify.entities.ValeurNutritionnelEntity;
import agrify.services.ServiceAnimal;
import agrify.services.ServiceAnimauxEnGestation;
import agrify.services.ServiceBesoinNutritionnel;
import agrify.services.ServiceIngredient;
import agrify.services.ServiceRation;
import agrify.services.ServiceValeurNutritionnel;
import agrify.services.ServiceValeurNutritionnelBesoin;
import agrify.utils.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alien kami sama
 */
public class AnimalManagementController implements Initializable {

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
private TextField rechercher_input;
        @FXML
    private Button searchingredient_btn;
    @FXML
    private Button button_logout_animal;       
    @FXML
public ComboBox<String> nom_ingredient_ration_selected;
        @FXML
public ComboBox<String> popup_combobox_unite_animal_amanagement1;
    @FXML
    
    private LineChart<String, Number> ingredient_quantity;
    @FXML
    private LineChart<String, Number> linechart_animal;
    @FXML
    private CategoryAxis x2;
    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private NumberAxis y2;
    @FXML
    private AnchorPane anchor_modify_ingredient;
    
    @FXML
    private AnchorPane animalManagementPagechef;
        @FXML
    private AnchorPane home;
    @FXML
    private AnchorPane anchor_delete_modifier_popup_ration;

    @FXML
    private AnchorPane anchor_enregistrer_popup_ration;

    @FXML
    private AnchorPane anchore_delete_modify_ingredient;

    @FXML
    private AnchorPane anchor_enregistrer_ingredient;
    @FXML
    private AnchorPane anchor_btn_enregistrer_animal;
    @FXML
    private Button btn_modifier_popup_animal_management;
    @FXML
    private Button btn_modifier_popup1_ration;
    @FXML
    private Button btn_supprimer_popup1_ration;
    @FXML
    private Button btn_supprimer_popup_animal_management;
    @FXML
    private AnchorPane anchor_btn_delete_modify_animal;
    
    
    @FXML
    private TextField adf_popup_besoin_nutritionnel;

    @FXML
    private TextField adf_popup_ingredient_management;

    @FXML
    private AnchorPane anchor_ajouter_ingrediant111;

    @FXML
    private AnchorPane anchor_animaux_management;

    @FXML
    private AnchorPane anchor_besoin_nutritionnel;

    @FXML
    private AnchorPane anchor_ration_management;

    @FXML
    private AnchorPane anchor_valeur_nutritionnelles02;

    @FXML
    private AnchorPane anchor_valeur_nutritionnelles1;

    @FXML
    private AnchorPane anchor_valeur_nutritionnelles2;

    @FXML
    private AnchorPane anchor_btn_enregistrer_besoin_nutritionnel;

    @FXML
    private AnchorPane anchor_btn_modify_delete_besoin_nutritionnel;

    @FXML
    private TableView<AnimauxEnGestationEntity> animauxEnGestationTable;

    @FXML
    private Button btn_modify_popup_besoin_nutritionnel;

    @FXML
    private Button btn_delete_popup_besoin_nutritionnel;

    @FXML
    private Button btn_ajouter_animal;

    @FXML
    private Button btn_ajouter_besoin1;

    @FXML
    private Button btn_animaux_side;

    @FXML
    private Button btn_besoin_nutritionnel;

    @FXML
    private Button btn_oui_modify_ingredients;

    @FXML
    private Button btn_non_modify_ingredients;

    @FXML
    private Button btn_betais_filter;

    @FXML
    private Button btn_betais_filter_animal_management;

    @FXML
    private Button btn_betais_filter_ration;

    @FXML
    private Button btn_cree_besoin;

    @FXML
    private Button btn_cree_ration;

    @FXML
    private Button btn_enregistrer;

    @FXML
    private Button btn_supprimer;

    @FXML
    private Button btn_modifier;

    @FXML
    private Button btn_enregistrer_popup1_ration;

    @FXML
    private Button btn_enregistrer_popup2_besoin_nutritionnel;

    @FXML
    private Button btn_enregistrer_popup2_ingrediant_management;

    @FXML
    private Button btn_enregistrer_popup_animal_management;

    @FXML
    private Button btn_enregistrer_popup_besoin_nutritionnel;

    @FXML
    private Button btn_enregistrer_popup2_ration;

    @FXML
    private Button btn_exit;

    @FXML
    private Button btn_exit1;

    @FXML
    private Button btn_exit2;

    @FXML
    private Button btn_exit3;

    @FXML
    private Button btn_exit4;

    @FXML
    private Button btn_exit5;

    @FXML
    private Button btn_exit6;

    @FXML
    private Button btn_ingredient;

    @FXML
    private Button btn_ingredient_indiv;

    @FXML
    private Button btn_nourriture_animale;

    @FXML
    private Button btn_ovins_filter;

    @FXML
    private Button btn_volaille_filter_animal_management;

    @FXML
    private Button btn_ovins_filter_ration;

    @FXML
    private Button btn_rations;

    @FXML
    private Button btn_reclamation;

    @FXML
    private Button btn_cree_ingredient;

    @FXML
    private Button btn_reinitialisation_filtre;

    @FXML
    private Button btn_reinitialisation_filtre1;

    @FXML
    private Button btn_reinitialisation_filtre2;

    @FXML
    private Button btn_reinitialisation_filtre3;

    @FXML
    private Button btn_tableau_bord;

    @FXML
    private Button btn_tache;

    @FXML
    private Button btn_tous_filter;

    @FXML
    private Button btn_tous_filter_animal_management;

    @FXML
    private Button btn_tous_filter_ration;

    @FXML
    private Button btn_tous_ingredient;

    @FXML
    private TextField ca_popup_besoin_nutritionnel;

    @FXML
    private TextField ca_popup_ingredient_management;

    @FXML
    private CheckBox chekbox_energie_popup_ingredient_management;

    @FXML
    private CheckBox chekbox_fibre_popup_ingredient_management;

    @FXML
    private CheckBox chekbox_mineral_popup_ingredient_management;

    @FXML
    private CheckBox chekbox_protien_popup_ingredient_management;

    @FXML
    private TableColumn<IngrediantEntity, String> colone_nom_ingredient_management;


    @FXML
    private TableColumn<Animal, String> colonne_age_animal_management;

    @FXML
    private TableColumn<BesoinNutritionnelsEntity, String> colonne_bute_production_besoin_nutritionnel;

    @FXML
    private TableColumn<IngrediantEntity, String> colonne_cout100_ingredient_management;

    @FXML
    private TableColumn<Animal, String> colonne_espece_animal_management;

    @FXML
    private TableColumn<BesoinNutritionnelsEntity, String> colonne_espece_besoin_nutritionnel;



    @FXML
    private TableColumn<BesoinNutritionnelsEntity, String> colonne_nom_produit_besoin_nutritionnel;

    @FXML
    private TableColumn<Animal, String> colonne_poids_animal_management;

    @FXML
    private TableColumn<BesoinNutritionnelsEntity, String> colonne_poids_max_besoin_nutritionnel;

    @FXML
    private TableColumn<BesoinNutritionnelsEntity, String> colonne_poidsmin_besoin_nutritionnel;

    @FXML
    private TableColumn<IngrediantEntity, String> colonne_principal_nutriment_ingredient_management;

    @FXML
    private TableColumn<Ration, String> colonne_ration_statut_production;

    @FXML
    private TableColumn<Ration, String> colonne_ration_poids_min;

    @FXML
    private TableColumn<Ration, String> colonne_ration_poids_max;

    @FXML
    private TableColumn<Ration, String> colonne_ration_sexe_animal;
    @FXML
    private TableColumn<Ration, String> colonne_ration_bute_production;
    @FXML
    private TableColumn<Animal, String> colonne_sexe_animal_management;

    @FXML
    private TableColumn<IngrediantEntity, String> colonne_source_ingredinet_management;

    @FXML
    private TableColumn<BesoinNutritionnelsEntity, String> colonne_status_besoin_nutritionnel;

    @FXML
    private TableColumn<Ration, String> coloone_ration_espece;

    @FXML
    private ComboBox<String> combobox_bute_production_popup2_ration;

    @FXML
    private ComboBox<String> combobox_popup_espece_ration;

    @FXML
    private ComboBox<String> combobox_udm_popup_ingredient_management;
    @FXML
    private TableColumn<AnimauxEnGestationEntity, Date> dashboard_elvage_imminent;
    @FXML
    private TableColumn<Animal, String> colonne_unit_animal_animal_management1;

    @FXML
    private TableColumn<AnimauxEnGestationEntity, Date> dashboard_espece_animal_gestation;

    @FXML
    private TableColumn<AnimauxEnGestationEntity, String> dashboard_dateAdv_animal_gestation;

    @FXML
    private TableColumn<AnimauxEnGestationEntity, Date> dashboard_prep_elevage;

    @FXML
    private TextArea description_popup_ingredient_management;

    @FXML
    private TextField eb_popup_besoin_nutritionnel;

    @FXML
    private TextField eb_popup_ingredient_management;

    @FXML
    private ComboBox<String> espece_combobox;

    @FXML
    private ComboBox<String> espece_combobox_animal_management;

    @FXML
    private ComboBox<String> espece_combobox_ration;
    @FXML
    private ComboBox<String> status_combobox_ration_popup;

    @FXML
    private TextField fb_popup_besoin_nutritionnel;

    @FXML
    private TextField fb_popup_ingredient_management;

    @FXML
    private TextField k_popup_besoin_nutritionnel;

    @FXML
    private TextField k_popup_ingredient_management;

    @FXML
    private Label label_betails_en_gestation;

    @FXML
    private Label label_nom_chef;

    @FXML
    private Label label_ovins_en_gestation;

    @FXML
    private Label label_poid_controller_animal_management;

    @FXML
    private Label label_poid_max_ration_controller;

    @FXML
    private Label label_poid_min_controller_animal_management;

    @FXML
    private Label label_poid_min_ration_controller;

    @FXML
    private Label label_rendement_mensuel_de_laine;

    @FXML
    private Label label_rendement_quotidien_en_miel;

    @FXML
    private Label label_rendement_quotidien_en_œufs;

    @FXML
    private Label label_title;

    @FXML
    private TextField mg_popup_besoin_nutritionnel;

    @FXML
    private TextField mg_popup_ingredient_management;

    @FXML
    private TextField ms_popup_besoin_nutritionnel;

    @FXML
    private TextField ms_popup_ingredient_management;

    @FXML
    private TextField ndf_popup_besoin_nutritionnel;

    @FXML
    private TextField ndf_popup_ingredient_management;

    @FXML
    private TextField nom_ingredient_popup_ingredient_management;

    @FXML
    private ComboBox<String> nutriment_principal_combobox;

    @FXML
    private TextField p_popup_besoin_nutritionnel;

    @FXML
    private TextField p_popup_ingredient_management;

    @FXML
    private AnchorPane page_animaux_management;

    @FXML
    private AnchorPane page_besoin_nutritionnels_management;

    @FXML
    private AnchorPane page_ingredient_main;

    @FXML
    private AnchorPane page_ration_managemnt;

    @FXML
    private AnchorPane page_tableau_bord;

    @FXML
    private TextField pb_popup_besoin_nutritionnel;

    @FXML
    private TextField pb_popup_ingredient_management;

    @FXML
    private ComboBox<String> plage_prix_combobox;

    @FXML
    private ComboBox<String> plage_quantite_combobox;

    @FXML
    private Slider popup2_poid_max_slider_ration;

    @FXML
    private Slider popup2_poid_min_slider_ration;

    @FXML
    private TextField popup_age_animal_management;

    @FXML
    private AnchorPane popup_animal_management;

    @FXML
    private AnchorPane popup_besoin_nutritionnels_management;

    @FXML
    private ComboBox<String> popup_combobox_espece_animal_amanagement;

    @FXML
    private ComboBox<String> popup_combox_bute_producion;

    @FXML
    private ComboBox<String> popup_espece_besoin_nutritionnel;

    @FXML
    private AnchorPane popup_ingredient_managment;

    @FXML
    private Label popup_poid_max_controller_besoin_nutritionnel;

    @FXML
    private Label popup_poid_min_controller_besoin_nutritionnel;

    @FXML
    private Slider popup_poids_max_slider_besoin_nutritionnel;

    @FXML
    private AnchorPane popup_ration_management;

    @FXML
    private TextField popup_sexe_animal_management;

    @FXML
    private TextField popup_sexe_besoin_nutritionnel;

    @FXML
    private Slider popup_slider_poids_min_besoin_nutritionnel;

    @FXML
    private ComboBox<String> popup_status_production_besoin_nutritionnel;

    @FXML
    private TextField prix_popup_ingredient_management;

    @FXML
    private TextField quantite_popup_ingredient_management;

    

    @FXML
    private TextField rechercher_input1;

    @FXML
    private TextField rechercher_input2;

    @FXML
    private TextField rechercher_input3;

    @FXML
    private TextField sexe_popup2_ration;

    @FXML
    private Slider slide_poids_animal_management;

    @FXML
    private TextArea source_popup_ingredient_management;

    @FXML
    private ComboBox<String> status_combobox;

    @FXML
    private ComboBox<String> status_combobox_ration;

    @FXML
    private TableView<Animal> table_anaimal_management;

    @FXML
    private TableView<BesoinNutritionnelsEntity> table_besoin_nutritionnel;

    @FXML
    private TableView<IngrediantEntity> table_igredient_management;

    @FXML
    private TableView<Ration> table_ration;

    @FXML
    private ComboBox<String> type_ingredient_popup_ingredient_management;
    //DataBase tools
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Statement statement;

    /*public void changeWithCombobox() {
    cree_ingredient_combobox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
    if ("ingredient individual".equals(newValue)) {
    popup_ingredient_managment.setVisible(true);
    //
    anchor_valeur_nutritionnelles1.setVisible(true);
    anchor_valeur_nutritionnelles2.setVisible(false);
    
    } else {
    popup_ingredient_managment.setVisible(false);
    }
    });
    }*/
@FXML
    void logout (ActionEvent event) throws IOException {
        
        
                Parent animalDashboardRoot = FXMLLoader.load(getClass().getResource("/agrify/views/signin.fxml"));
                Scene animalDashboardScene = new Scene(animalDashboardRoot);

                Stage animalDashboardStage = new Stage();
                animalDashboardStage.initStyle(StageStyle.TRANSPARENT);
                animalDashboardStage.setScene(animalDashboardScene);
                animalDashboardStage.show();

                Stage signInStage = (Stage) button_logout_animal.getScene().getWindow();
                signInStage.close();
    }
    //exit is done 
    public void exit(ActionEvent event) {
        if (event.getSource() == btn_exit) {
            popup_ingredient_managment.setVisible(false);
        } else if (event.getSource() == btn_exit1) {
            popup_ingredient_managment.setVisible(false);
        } else if (event.getSource() == btn_exit2) {
            popup_besoin_nutritionnels_management.setVisible(false);
        } else if (event.getSource() == btn_exit3) {
            popup_besoin_nutritionnels_management.setVisible(false);
        } else if (event.getSource() == btn_exit4) {
            popup_ration_management.setVisible(false);
        } else if (event.getSource() == btn_exit5) {
            popup_ration_management.setVisible(false);
        } else if (event.getSource() == btn_exit6) {
            popup_animal_management.setVisible(false);
        }
    }
    //

    // changerContinu est done
    public void changerContinu(ActionEvent event) {
        // pour accider lel gestion tableau de bord
        if (event.getSource() == btn_tableau_bord) {
            page_tableau_bord.setVisible(true);
            page_ingredient_main.setVisible(false);
            popup_ingredient_managment.setVisible(false);
            //
            anchor_valeur_nutritionnelles1.setVisible(false);
            anchor_valeur_nutritionnelles2.setVisible(false);
            //
            page_besoin_nutritionnels_management.setVisible(false);
            //
            popup_besoin_nutritionnels_management.setVisible(false);
            //
            anchor_besoin_nutritionnel.setVisible(false);
            anchor_valeur_nutritionnelles02.setVisible(false);
            //
            page_ration_managemnt.setVisible(false);
            //
            popup_ration_management.setVisible(false);
            //
            anchor_ration_management.setVisible(false);
            //
            page_animaux_management.setVisible(false);
            //
            popup_animal_management.setVisible(false);
            //
            anchor_animaux_management.setVisible(false);
            // pour accider lel gestion ingredient
        } else if (event.getSource() == btn_ingredient) {
            page_tableau_bord.setVisible(false);
            page_ingredient_main.setVisible(true);
            popup_ingredient_managment.setVisible(false);
            //
            anchor_valeur_nutritionnelles1.setVisible(false);
            anchor_valeur_nutritionnelles2.setVisible(false);
            //
            page_besoin_nutritionnels_management.setVisible(false);
            //
            popup_besoin_nutritionnels_management.setVisible(false);
            //
            anchor_besoin_nutritionnel.setVisible(false);
            anchor_valeur_nutritionnelles02.setVisible(false);
            //
            page_ration_managemnt.setVisible(false);
            //
            popup_ration_management.setVisible(false);
            //
            anchor_ration_management.setVisible(false);
            //
            page_animaux_management.setVisible(false);
            //
            popup_animal_management.setVisible(false);
            //
            anchor_animaux_management.setVisible(false);

            // pour accider lel gestion lel valeur % de neutrition 
        } else if (event.getSource() == btn_enregistrer_popup2_ingrediant_management) {
            page_tableau_bord.setVisible(false);
            page_ingredient_main.setVisible(true);
            popup_ingredient_managment.setVisible(true);
            //
            anchor_valeur_nutritionnelles1.setVisible(false);
            anchor_valeur_nutritionnelles2.setVisible(true);
            //
            page_besoin_nutritionnels_management.setVisible(false);
            //
            popup_besoin_nutritionnels_management.setVisible(false);
            //
            anchor_besoin_nutritionnel.setVisible(false);
            anchor_valeur_nutritionnelles02.setVisible(false);
            //
            page_ration_managemnt.setVisible(false);
            //
            popup_ration_management.setVisible(false);
            //
            anchor_ration_management.setVisible(false);
            //
            page_animaux_management.setVisible(false);
            //
            popup_animal_management.setVisible(false);
            //
            anchor_animaux_management.setVisible(false);
            // pour accider lel gestion lel gestion bsoin nutritionnel     
        } else if (event.getSource() == btn_besoin_nutritionnel) {
            page_tableau_bord.setVisible(false);
            page_ingredient_main.setVisible(false);
            popup_ingredient_managment.setVisible(false);
            //
            anchor_valeur_nutritionnelles1.setVisible(false);
            anchor_valeur_nutritionnelles2.setVisible(false);
            //
            page_besoin_nutritionnels_management.setVisible(true);
            //
            popup_besoin_nutritionnels_management.setVisible(false);
            //
            anchor_besoin_nutritionnel.setVisible(false);
            anchor_valeur_nutritionnelles02.setVisible(false);
            //
            page_ration_managemnt.setVisible(false);
            //
            popup_ration_management.setVisible(false);
            //
            anchor_ration_management.setVisible(false);
            //
            page_animaux_management.setVisible(false);
            //
            popup_animal_management.setVisible(false);
            //
            anchor_animaux_management.setVisible(false);
            // pour accider lel gestion lel creation bsoin nutritionnel  
        } else if (event.getSource() == btn_cree_besoin) {
            page_tableau_bord.setVisible(false);
            page_ingredient_main.setVisible(false);
            popup_ingredient_managment.setVisible(false);
            //
            anchor_valeur_nutritionnelles1.setVisible(false);
            anchor_valeur_nutritionnelles2.setVisible(false);
            //
            page_besoin_nutritionnels_management.setVisible(true);
            //
            popup_besoin_nutritionnels_management.setVisible(true);
            //
            anchor_besoin_nutritionnel.setVisible(true);
            anchor_valeur_nutritionnelles02.setVisible(false);
            anchor_btn_enregistrer_besoin_nutritionnel.setVisible(true);
            anchor_btn_modify_delete_besoin_nutritionnel.setVisible(false);

            //
            page_ration_managemnt.setVisible(false);
            //
            popup_ration_management.setVisible(false);
            //

            anchor_ration_management.setVisible(false);
            //
            page_animaux_management.setVisible(false);
            //
            popup_animal_management.setVisible(false);
            //
            anchor_animaux_management.setVisible(false);

            //pour acceder lel valeur de nutrition de la partie gestion de besoin nutritionnel 
        } else if (event.getSource() == btn_enregistrer_popup2_besoin_nutritionnel) {
            page_tableau_bord.setVisible(false);
            page_ingredient_main.setVisible(false);
            popup_ingredient_managment.setVisible(false);
            //
            anchor_valeur_nutritionnelles1.setVisible(false);
            anchor_valeur_nutritionnelles2.setVisible(false);
            //
            page_besoin_nutritionnels_management.setVisible(true);
            //
            popup_besoin_nutritionnels_management.setVisible(true);
            //
            anchor_besoin_nutritionnel.setVisible(false);
            anchor_valeur_nutritionnelles02.setVisible(true);
            //
            page_ration_managemnt.setVisible(false);
            //
            popup_ration_management.setVisible(false);
            //
            anchor_ration_management.setVisible(false);
            //
            page_animaux_management.setVisible(false);
            //
            popup_animal_management.setVisible(false);
            //
            anchor_animaux_management.setVisible(false);
        } else if (event.getSource() == btn_rations) {
            page_tableau_bord.setVisible(false);
            page_ingredient_main.setVisible(false);
            popup_ingredient_managment.setVisible(false);
            //
            anchor_valeur_nutritionnelles1.setVisible(false);
            anchor_valeur_nutritionnelles2.setVisible(false);
            //
            page_besoin_nutritionnels_management.setVisible(false);
            //
            popup_besoin_nutritionnels_management.setVisible(false);
            //
            anchor_besoin_nutritionnel.setVisible(false);
            anchor_valeur_nutritionnelles02.setVisible(false);
            //
            page_ration_managemnt.setVisible(true);
            //
            popup_ration_management.setVisible(false);
            //
            anchor_ration_management.setVisible(false);
            //
            page_animaux_management.setVisible(false);
            //
            popup_animal_management.setVisible(false);
            //
            anchor_animaux_management.setVisible(false);
        } else if (event.getSource() == btn_cree_ration) {
            page_tableau_bord.setVisible(false);
            page_ingredient_main.setVisible(false);
            popup_ingredient_managment.setVisible(false);
            //
            anchor_valeur_nutritionnelles1.setVisible(false);
            anchor_valeur_nutritionnelles2.setVisible(false);
            //
            page_besoin_nutritionnels_management.setVisible(false);
            //
            popup_besoin_nutritionnels_management.setVisible(false);
            //
            anchor_besoin_nutritionnel.setVisible(false);
            anchor_valeur_nutritionnelles02.setVisible(false);
            //
            // Show the ration management section
            page_ration_managemnt.setVisible(true);
            popup_ration_management.setVisible(true);
            anchor_ration_management.setVisible(true);
            anchor_enregistrer_popup_ration.setVisible(true);
            anchor_delete_modifier_popup_ration.setVisible(false);
            //
            page_animaux_management.setVisible(false);
            //
            popup_animal_management.setVisible(false);
            //
            anchor_animaux_management.setVisible(false);
        } else if (event.getSource() == btn_enregistrer_popup1_ration) {
            page_tableau_bord.setVisible(false);
            page_ingredient_main.setVisible(false);
            popup_ingredient_managment.setVisible(false);
            //
            anchor_valeur_nutritionnelles1.setVisible(false);
            anchor_valeur_nutritionnelles2.setVisible(false);
            //
            page_besoin_nutritionnels_management.setVisible(false);
            //
            popup_besoin_nutritionnels_management.setVisible(false);
            //
            anchor_besoin_nutritionnel.setVisible(false);
            anchor_valeur_nutritionnelles02.setVisible(false);
            //
            page_ration_managemnt.setVisible(true);
            //
            popup_ration_management.setVisible(true);
            //
            anchor_ration_management.setVisible(false);
            //
            page_animaux_management.setVisible(false);
            //
            popup_animal_management.setVisible(false);
            //
            anchor_animaux_management.setVisible(false);
        } else if (event.getSource() == btn_animaux_side) {
            page_tableau_bord.setVisible(false);
            page_ingredient_main.setVisible(false);
            popup_ingredient_managment.setVisible(false);
            //
            anchor_valeur_nutritionnelles1.setVisible(false);
            anchor_valeur_nutritionnelles2.setVisible(false);
            //
            page_besoin_nutritionnels_management.setVisible(false);
            //
            popup_besoin_nutritionnels_management.setVisible(false);
            //
            anchor_besoin_nutritionnel.setVisible(false);
            anchor_valeur_nutritionnelles02.setVisible(false);
            //
            page_ration_managemnt.setVisible(false);
            //
            popup_ration_management.setVisible(false);
            //
            anchor_ration_management.setVisible(false);
            //
            page_animaux_management.setVisible(true);
            //
            popup_animal_management.setVisible(false);
            //
            anchor_animaux_management.setVisible(false);
        } else if (event.getSource() == btn_ajouter_animal) {
            page_tableau_bord.setVisible(false);
            page_ingredient_main.setVisible(false);
            popup_ingredient_managment.setVisible(false);
            //
            anchor_valeur_nutritionnelles1.setVisible(false);
            anchor_valeur_nutritionnelles2.setVisible(false);
            //
            page_besoin_nutritionnels_management.setVisible(false);
            //
            popup_besoin_nutritionnels_management.setVisible(false);
            //
            anchor_besoin_nutritionnel.setVisible(false);
            anchor_valeur_nutritionnelles02.setVisible(false);
            //
            page_ration_managemnt.setVisible(false);
            //
            popup_ration_management.setVisible(false);
            //
            anchor_ration_management.setVisible(false);
            //
            // Show the animal management section
            page_animaux_management.setVisible(true);
            popup_animal_management.setVisible(true);
            anchor_animaux_management.setVisible(true);
            anchor_btn_enregistrer_animal.setVisible(true);
            anchor_btn_delete_modify_animal.setVisible(false);
        } else if (event.getSource() == btn_cree_ingredient) {
            page_tableau_bord.setVisible(false);
            page_ingredient_main.setVisible(true);
            popup_ingredient_managment.setVisible(true);
            anchore_delete_modify_ingredient.setVisible(false);
            anchor_enregistrer_ingredient.setVisible(true);
            //
            anchor_valeur_nutritionnelles1.setVisible(true);
            anchor_valeur_nutritionnelles2.setVisible(false);
            anchor_valeur_nutritionnelles1.setVisible(true);
            //
            page_besoin_nutritionnels_management.setVisible(false);
            //
            popup_besoin_nutritionnels_management.setVisible(false);
            //
            anchor_besoin_nutritionnel.setVisible(false);
            anchor_valeur_nutritionnelles02.setVisible(false);
            //
            page_ration_managemnt.setVisible(false);
            //
            popup_ration_management.setVisible(false);
            //
            anchor_ration_management.setVisible(false);
            //
            page_animaux_management.setVisible(false);
            //
            popup_animal_management.setVisible(false);
            //
            anchor_animaux_management.setVisible(false);
        }
    }

    //user 
    public void DisplayUserID() {
        try {
            Database db = Database.getInstance(); // Get the Database instance.
            connect = db.getConnection(); // Ensure 'connect' is properly initialized.

            if (connect != null) {
                String sql = "SELECT * FROM `user` WHERE `user_id` = 1";
                prepare = connect.prepareStatement(sql); // Initialize the prepared statement.
                result = prepare.executeQuery(); // Initialize the result set.

                if (result.next()) {
                    label_nom_chef.setText(result.getString("user_nom")+" "+result.getString("user_prenom"));
                }
            } else {
                System.out.println("Database connection is null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private List<IngrediantEntity> originalData;
    private List<BesoinNutritionnelsEntity> originalDataBesoinNutritionnels;
    private List<Ration> originalDataRation;
    private List<Animal> originalDataAnimal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            updateLabelWithTotalAnimalsInGestation();
            
            createAnimalWeightLineChart();
        } catch (SQLException ex) {
            Logger.getLogger(AnimalManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
       

        page_tableau_bord.setVisible(true);
        popup_combobox_unite_animal_amanagement1.setItems(FXCollections.observableArrayList("kg", "gram", "tonne"));
        nutriment_principal_combobox.setItems(FXCollections.observableArrayList("All", "Fibre", "Energie", "Proteine", "Mineral"));
        combobox_bute_production_popup2_ration.setItems(FXCollections.observableArrayList("Viande", "Lait", "Œufs"));
        combobox_popup_espece_ration.setItems(FXCollections.observableArrayList("Moutons", "Bovins", "Poules", "Dindes", "abeilles"));
        combobox_udm_popup_ingredient_management.setItems(FXCollections.observableArrayList("Metric Ton", "kilogram"));
        espece_combobox.setItems(FXCollections.observableArrayList("All", "Moutons", "Bovins", "Poules", "Dindes", "abeilles"));
        espece_combobox_animal_management.setItems(FXCollections.observableArrayList("All", "Moutons", "Bovins", "Poules", "Dindes", "abeilles"));
        espece_combobox_ration.setItems(FXCollections.observableArrayList("All", "Moutons", "Bovins", "Poules", "Dindes", "abeilles"));
        plage_prix_combobox.setItems(FXCollections.observableArrayList("All", "<1000", "1000-5000", "5000-10000", "10000<"));
        plage_quantite_combobox.setItems(FXCollections.observableArrayList("All", "<1000", "1000-5000", "5000-10000", "10000<"));
        popup_combobox_espece_animal_amanagement.setItems(FXCollections.observableArrayList("Moutons", "Bovins", "Poules", "Dindes", "abeilles"));
        popup_combox_bute_producion.setItems(FXCollections.observableArrayList("Viande", "Lait", "Œufs"));
        popup_espece_besoin_nutritionnel.setItems(FXCollections.observableArrayList("Moutons", "Bovins", "Poules", "Dindes", "abeilles"));
        popup_status_production_besoin_nutritionnel.setItems(FXCollections.observableArrayList("En Gestation", "En Lactation", "En Croissance", "Autre"));
        status_combobox.setItems(FXCollections.observableArrayList("All", "En Gestation", "En Lactation", "En Croissance", "Autre"));
        status_combobox_ration.setItems(FXCollections.observableArrayList("All", "En Gestation", "En Lactation", "En Croissance", "Autre"));
        type_ingredient_popup_ingredient_management.setItems(FXCollections.observableArrayList("ingredient individuel", "sacs de nourriture", "Autre"));
        status_combobox_ration_popup.setItems(FXCollections.observableArrayList("En Gestation", "En Lactation", "En Croissance", "Autre"));
        DisplayUserID();
        popup_poids_max_slider_besoin_nutritionnel.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update the label as the slider value changes
            popup_poid_max_controller_besoin_nutritionnel.setText(String.valueOf(newValue.intValue()));
        });

        popup_slider_poids_min_besoin_nutritionnel.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update the label as the slider value changes
            popup_poid_min_controller_besoin_nutritionnel.setText(String.valueOf(newValue.intValue()));
        });
        //
        popup2_poid_min_slider_ration.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update the label as the slider value changes
            label_poid_min_ration_controller.setText(String.valueOf(newValue.intValue()));
        });

        popup2_poid_max_slider_ration.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update the label as the slider value changes
            label_poid_max_ration_controller.setText(String.valueOf(newValue.intValue()));
        });
        //


        slide_poids_animal_management.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update the label as the slider value changes
            label_poid_controller_animal_management.setText(String.valueOf(newValue.intValue()));
        });
        //ingredient :!!!!!!!!!!!!!!!!!!!!!!
        //table views (ingredient)
        colone_nom_ingredient_management.setCellValueFactory(new PropertyValueFactory<>("nameIngredient"));
        colonne_source_ingredinet_management.setCellValueFactory(new PropertyValueFactory<>("loadedByIngredient"));
        colonne_cout100_ingredient_management.setCellValueFactory(new PropertyValueFactory<>("costIngredient"));
        colonne_principal_nutriment_ingredient_management.setCellValueFactory(new PropertyValueFactory<>("nutrimentPrincipalIngredient"));

        List<IngrediantEntity> ingrediants = loadIngrediantsFromDatabase();

        // Ajoutez les données à la table
        table_igredient_management.setItems(FXCollections.observableArrayList(ingrediants));
        table_igredient_management.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        // Retrieve the selected IngrediantEntity
                        IngrediantEntity selectedIngrediant = newValue;

                        // Call the displaySelectedIngrediant method to populate input fields
                        displaySelectedIngrediant(selectedIngrediant);
                    }
                }
        );
        //besoin nutritionnel !!!!!!!!!!!!!!!!!!!!!!!!!! :
        //table Views (besoins nutritionnel)    
        colonne_nom_produit_besoin_nutritionnel.setCellValueFactory(new PropertyValueFactory<>("sexeBesoinNutritionnel"));
        colonne_espece_besoin_nutritionnel.setCellValueFactory(new PropertyValueFactory<>("especeBesoinNutritionnel"));
        colonne_status_besoin_nutritionnel.setCellValueFactory(new PropertyValueFactory<>("statutProductionBesoinNutritionnel"));
        colonne_bute_production_besoin_nutritionnel.setCellValueFactory(new PropertyValueFactory<>("buteProductionBesoinNutritionnel"));
        colonne_poids_max_besoin_nutritionnel.setCellValueFactory(new PropertyValueFactory<>("poidsMaxBesoinNutritionnel"));
        colonne_poidsmin_besoin_nutritionnel.setCellValueFactory(new PropertyValueFactory<>("poidsMinBesoinNutritionnel"));
        List<BesoinNutritionnelsEntity> Besoins = loadBesoinFromDatabase();
        table_besoin_nutritionnel.setItems(FXCollections.observableArrayList(Besoins));
// this one is for the slectede besoin nutritionnel dans le tableau :
        table_besoin_nutritionnel.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        // Retrieve the selected BesoinNutritionnelsEntity
                        BesoinNutritionnelsEntity selectedBesoinNutritionnel = newValue;

                        // Call the displaySelectedBesoinNutritionnel method to populate input fields
                        displaySelectedBesoinNutritionnel(selectedBesoinNutritionnel);
                    }
                }
        );
        //
        ObservableList<AnimauxEnGestationEntity> animauxEnGestationList = loadDataFromDatabase();

        // Display the data in the table
        animauxEnGestationTable.setItems(animauxEnGestationList);

        // Associate table columns with entity properties using PropertyValueFactory
        dashboard_elvage_imminent.setCellValueFactory(new PropertyValueFactory<>("velagePrevu"));
        dashboard_espece_animal_gestation.setCellValueFactory(new PropertyValueFactory<>("espece"));
        dashboard_dateAdv_animal_gestation.setCellValueFactory(new PropertyValueFactory<>("dateAvertissement"));
        dashboard_prep_elevage.setCellValueFactory(new PropertyValueFactory<>("preparationVelage"));
        //
        ObservableList<Animal> animalList = loadAnimalFromDatabase();
        colonne_unit_animal_animal_management1.setCellValueFactory(new PropertyValueFactory<>("unit animal"));
        colonne_espece_animal_management.setCellValueFactory(new PropertyValueFactory<>("espece"));
        colonne_poids_animal_management.setCellValueFactory(new PropertyValueFactory<>("poids"));
        colonne_unit_animal_animal_management1.setCellValueFactory(new PropertyValueFactory<>("unit_animal"));
        colonne_sexe_animal_management.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        colonne_age_animal_management.setCellValueFactory(new PropertyValueFactory<>("age"));
        table_anaimal_management.setItems(animalList);

        table_anaimal_management.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        // Retrieve the selected BesoinNutritionnelsEntity
                        Animal animal = newValue;

                        // Call the displaySelectedAnimal method to populate input fields
                        displaySelectedAnimal(animal);
                    }
                }
        );
        ObservableList<Ration> rationList = FXCollections.observableArrayList(loadRationDataFromDatabase());

        // Set the cell value factories for the Ration columns
        colonne_ration_statut_production.setCellValueFactory(new PropertyValueFactory<>("statutRation"));
        colonne_ration_poids_min.setCellValueFactory(new PropertyValueFactory<>("poidsMinRation"));
        colonne_ration_poids_max.setCellValueFactory(new PropertyValueFactory<>("poidsMaxRation"));
        colonne_ration_sexe_animal.setCellValueFactory(new PropertyValueFactory<>("sexeRation"));
        coloone_ration_espece.setCellValueFactory(new PropertyValueFactory<>("especeRation"));
        colonne_ration_bute_production.setCellValueFactory(new PropertyValueFactory<>("buteProductionRation"));
        table_ration.setItems(rationList);

        table_ration.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        // Retrieve the selected Ration
                        Ration ration = newValue;

                        // Call a method to populate input fields or perform other actions with the selected Ration
                        displaySelectedRation(ration);
                    }
                }
        );
        try {
            createIngredientQuantityLineChart();
        } catch (SQLException ex) {
            Logger.getLogger(AnimalManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
//combobox
        List<IngrediantEntity> ingredients = loadIngrediantsFromDatabase();
        ObservableList<IngrediantEntity> dataingredient = FXCollections.observableArrayList(ingredients);

        table_igredient_management.setItems(dataingredient);

        nutriment_principal_combobox.valueProperty().addListener((observable, oldValue, newValue) -> {
            String selectedNutriment = newValue;
            dataingredient.setAll(filterByNutriment(ingredients, selectedNutriment));
        });
        plage_prix_combobox.setOnAction(event -> {
            String selectedValue = plage_prix_combobox.getValue();
            dataingredient.setAll(filterByPriceRange(ingredients, selectedValue));
        });

        plage_quantite_combobox.setOnAction(event -> {
            String selectedValue = plage_quantite_combobox.getValue();
            dataingredient.setAll(filterByQuantityRange(ingredients, selectedValue));
        });
        List<BesoinNutritionnelsEntity> besoin = loadBesoinFromDatabase();
        ObservableList<BesoinNutritionnelsEntity> dataBesoin = FXCollections.observableArrayList(besoin);
        table_besoin_nutritionnel.setItems(dataBesoin);
        espece_combobox.valueProperty().addListener((observable, oldValue, newValue) -> {
            String selectedNutriment = newValue;
            dataBesoin.setAll(filterByespeceBesoin(besoin, selectedNutriment));
        });
        status_combobox.valueProperty().addListener((observable, oldValue, newValue) -> {
            String selectedNutriment = newValue;
            dataBesoin.setAll(filterBystatusBesoin(besoin, selectedNutriment));
        });

        List<Ration> ration = loadRationDataFromDatabase();
        ObservableList<Ration> dataRation = FXCollections.observableArrayList(ration);
        table_ration.setItems(dataRation);
        espece_combobox_ration.valueProperty().addListener((observable, oldValue, newValue) -> {
            String selectedNutriment = newValue;
            dataRation.setAll(filterByespecetRation(ration, selectedNutriment));
        });
        status_combobox_ration.valueProperty().addListener((observable, oldValue, newValue) -> {
            String selectedNutriment = newValue;
            dataRation.setAll(filterBystatusRation(ration, selectedNutriment));
        });

        List<Animal> animal = loadAnimalFromDatabase();
        ObservableList<Animal> dataAnimal = FXCollections.observableArrayList(animal);
        table_anaimal_management.setItems(dataAnimal);
        espece_combobox_animal_management.valueProperty().addListener((observable, oldValue, newValue) -> {
            String selectedNutriment = newValue;
            dataAnimal.setAll(filterByespecetAnimal(animal, selectedNutriment));
        });

        //refrech filter
        originalData = loadIngrediantsFromDatabase();
        originalDataBesoinNutritionnels = loadBesoinFromDatabase();
        originalDataRation = loadRationDataFromDatabase();
        originalDataAnimal = loadAnimalFromDatabase();

        //buttons
        ServiceRation serviceRation = new ServiceRation(Database.getInstance().getConnection());
        List<Ration> rations = serviceRation.getSpecificColumnsFromDatabase();
        btn_ovins_filter_ration.setOnAction(event -> {
            List<String> selectedSpecies = Arrays.asList("Poules", "Dindes", "poules", "dindes"); // Add more species as needed
            List<Ration> filteredList = filterBySpeciesRation(rations, selectedSpecies);

            // Update your table view with the filtered list
            table_ration.getItems().setAll(filteredList);

        });
        btn_betais_filter_ration.setOnAction(event -> {
            List<String> selectedSpecies = Arrays.asList("Moutons", "Bovins", "moutons", "bovins", "mouton"); // Add more species as needed
            List<Ration> filteredList = filterBySpeciesRation(rations, selectedSpecies);

            // Update your table view with the filtered list
            table_ration.getItems().setAll(filteredList);

        });

        ServiceAnimal service = new ServiceAnimal(Database.getInstance().getConnection());

        List<Animal> animals = service.getSpecificColumnsFromDatabase();
        btn_volaille_filter_animal_management.setOnAction(event -> {
            List<String> selectedSpecies = Arrays.asList("Poulets", "Dindes", "poulets", "dindes"); // Add more species as needed
            List<Animal> filteredList = filterBySpecies(animals, selectedSpecies);

            // Update your table view with the filtered list
            table_anaimal_management.getItems().setAll(filteredList);

        });
        btn_betais_filter_animal_management.setOnAction(event -> {
            List<String> selectedSpecies = Arrays.asList("Moutons", "Bovins", "moutons", "bovins", "mouton","chèvres","Chèvres"); // Add more species as needed
            List<Animal> filteredList = filterBySpecies(animals, selectedSpecies);

            // Update your table view with the filtered list
            table_anaimal_management.getItems().setAll(filteredList);

        });
        ServiceBesoinNutritionnel serviceBESOIN = new ServiceBesoinNutritionnel(Database.getInstance().getConnection());
        // Implement this method to fetch specific columns from the database
        List<BesoinNutritionnelsEntity> besoins = serviceBESOIN.getSpecificColumnsFromDatabase();
        btn_ovins_filter.setOnAction(event -> {
            List<String> selectedSpecies = Arrays.asList("Poules", "Dindes", "poules", "dindes"); // Add more species as needed
            List<BesoinNutritionnelsEntity> filteredList = filterBySpeciesBesoin(besoins, selectedSpecies);

            // Update your table view with the filtered list
            table_besoin_nutritionnel.getItems().setAll(filteredList);

        });
        btn_betais_filter.setOnAction(event -> {
            List<String> selectedSpecies = Arrays.asList("Moutons", "Bovins", "moutons", "bovins", "mouton"); // Add more species as needed
            List<BesoinNutritionnelsEntity> filteredList = filterBySpeciesBesoin(besoins, selectedSpecies);

            // Update your table view with the filtered list
            table_besoin_nutritionnel.getItems().setAll(filteredList);

        });

    }

 

    @FXML
    private void refreshFilters() {

        List<IngrediantEntity> filteredIngredients = originalData;
        List<BesoinNutritionnelsEntity> filteredBesoinNutritionnels = originalDataBesoinNutritionnels;
        List<Ration> filteredRation = originalDataRation;
        List<Animal> filteredAnimal = originalDataAnimal;

        table_igredient_management.getItems().setAll(filteredIngredients);
        table_besoin_nutritionnel.getItems().setAll(filteredBesoinNutritionnels);
        table_ration.getItems().setAll(filteredRation);
        table_anaimal_management.getItems().setAll(filteredAnimal);
    }

    private List<IngrediantEntity> filterByNutriment(List<IngrediantEntity> ingredients, String selectedNutriment) {
        if (selectedNutriment == null || selectedNutriment.isEmpty() || selectedNutriment.equals("All")) {
            return ingredients; // Return all ingredients if no nutriment is selected or "All" is selected
        }

        return ingredients.stream()
                .filter(ingredient -> {
                    String nutriment = ingredient.getNutrimentPrincipalIngredient();
                    return nutriment != null && nutriment.contains(selectedNutriment);
                })
                .collect(Collectors.toList());
    }

    //2
    private List<BesoinNutritionnelsEntity> filterBystatusBesoin(List<BesoinNutritionnelsEntity> besoins, String selectedStatut) {
        if (selectedStatut == null || selectedStatut.isEmpty() || selectedStatut.equals("All")) {
            return besoins;
        }

        return besoins.stream()
                .filter(besoin -> {
                    String statut = besoin.getStatutProductionBesoinNutritionnel();
                    return statut != null && statut.equals(selectedStatut);
                })
                .collect(Collectors.toList());
    }

    private List<BesoinNutritionnelsEntity> filterByespeceBesoin(List<BesoinNutritionnelsEntity> besoins, String selectedEspece) {
        if (selectedEspece == null || selectedEspece.isEmpty() || selectedEspece.equals("All")) {
            return besoins;
        }

        return besoins.stream()
                .filter(besoin -> {
                    String espece = besoin.getEspeceBesoinNutritionnel();
                    return espece != null && espece.equals(selectedEspece);
                })
                .collect(Collectors.toList());
    }

    private List<Ration> filterBystatusRation(List<Ration> rations, String selectedStatut) {
        if (selectedStatut == null || selectedStatut.isEmpty() || selectedStatut.equals("All")) {
            return rations;
        }

        return rations.stream()
                .filter(ration -> {
                    String statut = ration.getStatutRation();
                    return statut != null && statut.equals(selectedStatut);
                })
                .collect(Collectors.toList());
    }

    private List<Ration> filterByespecetRation(List<Ration> rations, String selectedEspece) {
        if (selectedEspece == null || selectedEspece.isEmpty() || selectedEspece.equals("All")) {
            return rations;
        }

        return rations.stream()
                .filter(ration -> {
                    String espece = ration.getEspeceRation();
                    return espece != null && espece.equals(selectedEspece);
                })
                .collect(Collectors.toList());
    }

    private List<Animal> filterByespecetAnimal(List<Animal> animals, String selectedEspece) {
        if (selectedEspece == null || selectedEspece.isEmpty() || selectedEspece.equals("All")) {
            return animals;
        }

        return animals.stream()
                .filter(animal -> {
                    String espece = animal.getEspece();
                    return espece != null && espece.equals(selectedEspece);
                })
                .collect(Collectors.toList());
    }

    private List<IngrediantEntity> filterByPriceRange(List<IngrediantEntity> ingredients, String selectedValue) {
        if (selectedValue == null) {
            return ingredients;
        }

        return ingredients.stream()
                .filter(ingredient -> {
                    double price = ingredient.getCostIngredient();
                    if (selectedValue.equals("<1000")) {
                        return price < 1000;
                    } else if (selectedValue.equals("1000-5000")) {
                        return price >= 1000 && price <= 5000;
                    } else if (selectedValue.equals("5000-10000")) {
                        return price > 5000 && price <= 10000;
                    } else if (selectedValue.equals("10000<")) {
                        return price > 10000;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    private List<IngrediantEntity> filterByQuantityRange(List<IngrediantEntity> ingredients, String selectedValue) {
        if (selectedValue == null) {
            return ingredients;
        }

        return ingredients.stream()
                .filter(ingredient -> {
                    double itemQuantityIngredient = ingredient.getItemQuantityIngredient();
                    if (selectedValue.equals("<1000")) {
                        return itemQuantityIngredient < 1000;
                    } else if (selectedValue.equals("1000-5000")) {
                        return itemQuantityIngredient >= 1000 && itemQuantityIngredient <= 5000;
                    } else if (selectedValue.equals("5000-10000")) {
                        return itemQuantityIngredient > 5000 && itemQuantityIngredient <= 10000;
                    } else if (selectedValue.equals("10000<")) {
                        return itemQuantityIngredient > 10000;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
    //buttons

    private List<Animal> filterBySpecies(List<Animal> animals, List<String> selectedSpecies) {
        List<Animal> filteredList = new ArrayList();

        for (Animal animal : animals) {
            if (selectedSpecies.contains(animal.getEspece())) {
                filteredList.add(animal);
            }
        }

        return filteredList;
    }

    private List<BesoinNutritionnelsEntity> filterBySpeciesBesoin(List<BesoinNutritionnelsEntity> besoins, List<String> selectedSpecies) {
        List<BesoinNutritionnelsEntity> filteredList = new ArrayList();

        for (BesoinNutritionnelsEntity besoin : besoins) {
            if (selectedSpecies.contains(besoin.getEspeceBesoinNutritionnel())) {
                filteredList.add(besoin);
            }
        }

        return filteredList;
    }

    private List<Ration> filterBySpeciesRation(List<Ration> rations, List<String> selectedSpecies) {
        List<Ration> filteredList = new ArrayList();

        for (Ration ration : rations) {
            if (selectedSpecies.contains(ration.getEspeceRation())) {
                filteredList.add(ration);
            }
        }

        return filteredList;
    }
    private List<IngrediantEntity> ingredients;
//search 

@FXML
private void handleSearchButtonClick() {
    try {
        // ... existing code ...
String searchTerm = rechercher_input.getText().toLowerCase();
        // Perform the search and get the results
        List<IngrediantEntity> searchResult = searchByName(ingredients, searchTerm);

        // Update the TableView with the search results
        table_igredient_management.getItems().setAll(searchResult);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private List<IngrediantEntity> searchByName(List<IngrediantEntity> ingredients, String searchTerm) {
    List<IngrediantEntity> result = new ArrayList<>();

    // Check for null ingredients list
    if (ingredients == null) {
        return result;
    }

    for (IngrediantEntity ingrediant : ingredients) {
        // Assuming you want to search by name (modify as needed)
        if (ingrediant.getNameIngredient() != null && ingrediant.getNameIngredient().toLowerCase().contains(searchTerm)) {
            result.add(ingrediant);
        }
    }

    return result;
}

   
 
    
    
// Set the items to your TableView directly
    //tableview (affichage)
    private ObservableList<AnimauxEnGestationEntity> loadDataFromDatabase() {
        // Implement this method to fetch data from your database
        ServiceAnimauxEnGestation service = new ServiceAnimauxEnGestation(Database.getInstance().getConnection());
        // Example of fetching data (replace with your actual database query)
        List<AnimauxEnGestationEntity> dataFromDatabase = service.getSpecificColumnsFromDatabase();
        return FXCollections.observableArrayList(dataFromDatabase);
    }

    private ObservableList<BesoinNutritionnelsEntity> loadBesoinFromDatabase() {
        ServiceBesoinNutritionnel service = new ServiceBesoinNutritionnel(Database.getInstance().getConnection());
        // Implement this method to fetch specific columns from the database
        List<BesoinNutritionnelsEntity> dataFromDatabase = service.getSpecificColumnsFromDatabase();
        return FXCollections.observableArrayList(dataFromDatabase);
    }

    private ObservableList<IngrediantEntity> loadIngrediantsFromDatabase() {

        ServiceIngredient service = new ServiceIngredient(Database.getInstance().getConnection());

        List<IngrediantEntity> dataFromDatabase = service.getSpecificColumnsFromDatabase();
        return FXCollections.observableArrayList(dataFromDatabase);
    }

    private ObservableList<Animal> loadAnimalFromDatabase() {
        ServiceAnimal service = new ServiceAnimal(Database.getInstance().getConnection());

        List<Animal> dataFromDatabase = service.getSpecificColumnsFromDatabase();
        return FXCollections.observableArrayList(dataFromDatabase);
    }

    private List<Ration> loadRationDataFromDatabase() {
        // Use your ServiceRation class or another method to fetch data from your database
        // Example:
        ServiceRation serviceRation = new ServiceRation(Database.getInstance().getConnection());
        List<Ration> dataFromDatabase = serviceRation.getSpecificColumnsFromDatabase(); // Replace with your actual database query
        return FXCollections.observableArrayList(dataFromDatabase);
    }

    // besoin nutritionnel:!!!!!!!!!!!!!!!!!!!!!!!!!!
    //display inside the popup selected raw
    private void displaySelectedBesoinNutritionnel(BesoinNutritionnelsEntity selectedBesoinNutritionnel) {
        page_tableau_bord.setVisible(false);
        page_ingredient_main.setVisible(false);
        popup_ingredient_managment.setVisible(false);
        //
        anchor_valeur_nutritionnelles1.setVisible(false);
        anchor_valeur_nutritionnelles2.setVisible(false);
        //
        page_besoin_nutritionnels_management.setVisible(true);
        //
        popup_besoin_nutritionnels_management.setVisible(true);
        //
        anchor_besoin_nutritionnel.setVisible(true);
        anchor_valeur_nutritionnelles02.setVisible(false);
        anchor_btn_enregistrer_besoin_nutritionnel.setVisible(false);
        anchor_btn_modify_delete_besoin_nutritionnel.setVisible(true);
        //
        page_ration_managemnt.setVisible(false);
        //
        popup_ration_management.setVisible(false);
        //
        anchor_ration_management.setVisible(false);
        //
        page_animaux_management.setVisible(false);
        //
        popup_animal_management.setVisible(false);
        //
        anchor_animaux_management.setVisible(false);
        // Populate your input fields with the selected data
        popup_espece_besoin_nutritionnel.setValue(selectedBesoinNutritionnel.getEspeceBesoinNutritionnel());
        popup_status_production_besoin_nutritionnel.setValue(selectedBesoinNutritionnel.getStatutProductionBesoinNutritionnel());
        popup_sexe_besoin_nutritionnel.setText(selectedBesoinNutritionnel.getSexeBesoinNutritionnel());
        popup_slider_poids_min_besoin_nutritionnel.setValue(selectedBesoinNutritionnel.getPoidsMinBesoinNutritionnel());
        popup_poids_max_slider_besoin_nutritionnel.setValue(selectedBesoinNutritionnel.getPoidsMaxBesoinNutritionnel());
        popup_combox_bute_producion.setValue(selectedBesoinNutritionnel.getButeProductionBesoinNutritionnel());
    }

    //ajout
    @FXML
    void addBesoinNutritionnel(ActionEvent event) throws IOException {
        page_tableau_bord.setVisible(false);
        page_ingredient_main.setVisible(false);
        popup_ingredient_managment.setVisible(false);
        //
        anchor_valeur_nutritionnelles1.setVisible(false);
        anchor_valeur_nutritionnelles2.setVisible(false);
        //
        page_besoin_nutritionnels_management.setVisible(true);
        //
        popup_besoin_nutritionnels_management.setVisible(true);
        //
        anchor_besoin_nutritionnel.setVisible(false);
        anchor_valeur_nutritionnelles02.setVisible(true);
        anchor_btn_enregistrer_besoin_nutritionnel.setVisible(true);
        anchor_btn_modify_delete_besoin_nutritionnel.setVisible(false);
        //
        page_ration_managemnt.setVisible(false);
        //
        popup_ration_management.setVisible(false);
        //
        anchor_ration_management.setVisible(false);

        //
        page_animaux_management.setVisible(false);
        //
        popup_animal_management.setVisible(false);
        //
        anchor_animaux_management.setVisible(false);
        System.out.println("Database instance connection");
        System.out.println(Database.getInstance().getConnection());

        // Retrieve values from UI elements
        String espece = popup_espece_besoin_nutritionnel.getValue();
        String statutProduction = popup_status_production_besoin_nutritionnel.getValue();
        String sexe = popup_sexe_besoin_nutritionnel.getText();
        double poidsMin = popup_slider_poids_min_besoin_nutritionnel.getValue();
        double poidsMax = popup_poids_max_slider_besoin_nutritionnel.getValue();
        String buteProduction = popup_combox_bute_producion.getValue();

        // Create a BesoinNutritionnelEntity object
        BesoinNutritionnelsEntity besoinNutritionnel = new BesoinNutritionnelsEntity(espece, statutProduction, sexe, poidsMin, poidsMax, buteProduction);
        System.out.println(besoinNutritionnel);

        // Create a confirmation dialog for adding
        Alert confirmation = new Alert(AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation d'ajout");
        confirmation.setHeaderText("Confirmation d'ajout");
        confirmation.setContentText("Êtes-vous sûr de vouloir ajouter ce besoin nutritionnel ?");

        // Add OK and Cancel buttons to the confirmation dialog
        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
        confirmation.getButtonTypes().setAll(okButton, cancelButton);

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = confirmation.showAndWait();
        if (popup_espece_besoin_nutritionnel.getValue() == null ||
        popup_status_production_besoin_nutritionnel.getValue() == null ||
        popup_sexe_besoin_nutritionnel.getText().isEmpty() ||
        popup_combox_bute_producion.getValue() == null) {
                    anchor_besoin_nutritionnel.setVisible(true);
        anchor_valeur_nutritionnelles02.setVisible(false);
        // Display an error message or handle the empty fields as needed
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setTitle("Erreur");
        errorAlert.setHeaderText("Champs vides");
        errorAlert.setContentText("Veuillez remplir tous les champs avant d'ajouter le besoin nutritionnel.");
        errorAlert.showAndWait();
        return; // Exit the method if any field is empty
    }
        else{ if (result.isPresent() && result.get() == okButton) {
            // The user confirmed the addition
            // Assuming that table_besoin_nutritionnel is your TableView and
            // besoinNutritionnelsList is the ObservableList that backs it.
            ObservableList<BesoinNutritionnelsEntity> besoinNutritionnelsList = table_besoin_nutritionnel.getItems();
            besoinNutritionnelsList.add(besoinNutritionnel);

            // Refresh the TableView to reflect the changes
            table_besoin_nutritionnel.refresh();
        }}
    }

    //modify 
    @FXML
    private void modifySelectedBesoinNutritionnel() {
        // Get the selected BesoinNutritionnelsEntity from the table view
        BesoinNutritionnelsEntity selectedBesoinNutritionnel = table_besoin_nutritionnel.getSelectionModel().getSelectedItem();

        if (selectedBesoinNutritionnel != null) {
            // Get the updated values from your input fields
            String espece = popup_espece_besoin_nutritionnel.getValue();
            String statutProduction = popup_status_production_besoin_nutritionnel.getValue();
            String sexe = popup_sexe_besoin_nutritionnel.getText();
            double poidsMin = popup_slider_poids_min_besoin_nutritionnel.getValue();
            double poidsMax = popup_poids_max_slider_besoin_nutritionnel.getValue();
            String buteProduction = popup_combox_bute_producion.getValue();

            // Create a confirmation dialog for modification
            Alert confirmation = new Alert(AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation de Modification");
            confirmation.setHeaderText("Confirmation de Modification");
            confirmation.setContentText("Êtes-vous sûr de vouloir modifier ce besoin nutritionnel ?");

            // Add OK and Cancel buttons to the confirmation dialog
            ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
            confirmation.getButtonTypes().setAll(okButton, cancelButton);

            // Show the confirmation dialog and wait for the user's response
            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.isPresent() && result.get() == okButton) {
                // The user confirmed the modification
                // Update the selectedBesoinNutritionnel
                selectedBesoinNutritionnel.setEspeceBesoinNutritionnel(espece);
                selectedBesoinNutritionnel.setStatutProductionBesoinNutritionnel(statutProduction);
                selectedBesoinNutritionnel.setSexeBesoinNutritionnel(sexe);
                selectedBesoinNutritionnel.setPoidsMinBesoinNutritionnel(poidsMin);
                selectedBesoinNutritionnel.setPoidsMaxBesoinNutritionnel(poidsMax);
                selectedBesoinNutritionnel.setButeProductionBesoinNutritionnel(buteProduction);

                // Now, update the entity in your database
                ServiceBesoinNutritionnel service = new ServiceBesoinNutritionnel(Database.getInstance().getConnection());
                service.update(selectedBesoinNutritionnel);

                // Refresh the table view
                table_besoin_nutritionnel.refresh();
            }
        } else {
            System.out.println("No item selected for modification.");
        }
    }

    //supprime
    @FXML
    private void deleteSelectedBesoinNutritionnel() {
        // Récupérez l'élément sélectionné dans la table
        BesoinNutritionnelsEntity selectedBesoinNutritionnel = table_besoin_nutritionnel.getSelectionModel().getSelectedItem();

        if (selectedBesoinNutritionnel != null) {
            int idBesoinNutritionnel = selectedBesoinNutritionnel.getIdBesoinNutritionnel(); // Remplacez cela par le vrai moyen d'obtenir l'ID

            // Créez une boîte de dialogue de confirmation
            Alert confirmation = new Alert(AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation de Suppression");
            confirmation.setHeaderText("Confirmation de Suppression");
            confirmation.setContentText("Êtes-vous sûr de vouloir supprimer ce besoin nutritionnel ?");

            // Ajoutez des boutons OK et Annuler à la boîte de dialogue
            ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
            confirmation.getButtonTypes().setAll(okButton, cancelButton);

            // Affichez la boîte de dialogue de confirmation et attendez la réponse de l'utilisateur
            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.isPresent() && result.get() == okButton) {
                // L'utilisateur a confirmé la suppression
                ServiceBesoinNutritionnel service = new ServiceBesoinNutritionnel(Database.getInstance().getConnection());
                service.supprimer(idBesoinNutritionnel);

                // Mettez à jour la table après la suppression
                table_besoin_nutritionnel.getItems().remove(selectedBesoinNutritionnel);
            }
        } else {
            // Aucun élément sélectionné, affichez un message d'erreur
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un élément à supprimer.");
            alert.showAndWait();
        }
    }

    // valeur nutritionnel 
    @FXML
void addValeurNutritionnelBesoin(ActionEvent event) throws IOException {
    // Check if any of the text fields are empty
     System.out.println("Database instance connection");
        System.out.println(Database.getInstance().getConnection());
        ServiceValeurNutritionnelBesoin serviceValeurNutritionnelBesoin
                = new ServiceValeurNutritionnelBesoin(Database.getInstance().getConnection());
    if (pb_popup_besoin_nutritionnel.getText().isEmpty() ||
        fb_popup_besoin_nutritionnel.getText().isEmpty() ||
        adf_popup_besoin_nutritionnel.getText().isEmpty() ||
        ndf_popup_besoin_nutritionnel.getText().isEmpty() ||
        ms_popup_besoin_nutritionnel.getText().isEmpty() ||
        eb_popup_besoin_nutritionnel.getText().isEmpty() ||
        ca_popup_besoin_nutritionnel.getText().isEmpty() ||
        p_popup_besoin_nutritionnel.getText().isEmpty() ||
        mg_popup_besoin_nutritionnel.getText().isEmpty() ||
        k_popup_besoin_nutritionnel.getText().isEmpty()) {
        // Display an error message or handle the empty fields as needed
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Empty Fields");
        errorAlert.setContentText("Please fill in all fields before adding nutritional values.");
        errorAlert.showAndWait();
        return; // Exit the method if any field is empty
    }

    try {
        // Parse the text values to double
        double pb = Double.parseDouble(pb_popup_besoin_nutritionnel.getText());
        double fb = Double.parseDouble(fb_popup_besoin_nutritionnel.getText());
        double adf = Double.parseDouble(adf_popup_besoin_nutritionnel.getText());
        double ndf = Double.parseDouble(ndf_popup_besoin_nutritionnel.getText());
        double ms = Double.parseDouble(ms_popup_besoin_nutritionnel.getText());
        double eb = Double.parseDouble(eb_popup_besoin_nutritionnel.getText());
        double ca = Double.parseDouble(ca_popup_besoin_nutritionnel.getText());
        double p = Double.parseDouble(p_popup_besoin_nutritionnel.getText());
        double mg = Double.parseDouble(mg_popup_besoin_nutritionnel.getText());
        double k = Double.parseDouble(k_popup_besoin_nutritionnel.getText());

        // Create a ValeurNutritionnelBesoinNutritionnelEntity object
        ValeurNutritionnelBesoinNutritionnelEntity nutritionalValues = new ValeurNutritionnelBesoinNutritionnelEntity(pb, fb, adf, ndf, ms, eb, ca, p, mg, k);
        System.out.println(nutritionalValues);

        // Create a confirmation dialog
        Alert confirmation = new Alert(AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Addition");
        confirmation.setHeaderText("Confirm Addition");
        confirmation.setContentText("Are you sure you want to add these nutritional values for the need?");

        // Add OK and Cancel buttons to the dialog
        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        confirmation.getButtonTypes().setAll(okButton, cancelButton);

        // Show the confirmation dialog and wait for user input
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == okButton) {
            // User confirmed the addition
            serviceValeurNutritionnelBesoin.ajouter(nutritionalValues);
        }
    } catch (NumberFormatException e) {
        // Handle the case where parsing to double fails (invalid input)
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Invalid Input");
        errorAlert.setContentText("Please enter valid numerical values for the nutritional fields.");
        errorAlert.showAndWait();
    }
}


    //INGREDIENT CONTROLLERS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //ajouter
    @FXML
    void addIngredient(ActionEvent event) throws IOException {
        page_tableau_bord.setVisible(false);
        page_ingredient_main.setVisible(true);
        popup_ingredient_managment.setVisible(true);
        //
        anchor_valeur_nutritionnelles1.setVisible(false);
        anchor_valeur_nutritionnelles2.setVisible(true);
        //
        page_besoin_nutritionnels_management.setVisible(false);
        //
        popup_besoin_nutritionnels_management.setVisible(false);
        //
        anchor_besoin_nutritionnel.setVisible(false);
        anchor_valeur_nutritionnelles02.setVisible(false);
        //
        page_ration_managemnt.setVisible(false);
        //
        popup_ration_management.setVisible(false);
        //
        anchor_ration_management.setVisible(false);
        //
        page_animaux_management.setVisible(false);
        //
        popup_animal_management.setVisible(false);
        //
        anchor_animaux_management.setVisible(false);
        System.out.println("Database instance connection");
        System.out.println(Database.getInstance().getConnection());

        // Create an Ingredient object and populate it with data from the input fields
        String nom = nom_ingredient_popup_ingredient_management.getText();
        String type = type_ingredient_popup_ingredient_management.getValue();
        String description = description_popup_ingredient_management.getText();
        double prix = Double.parseDouble(prix_popup_ingredient_management.getText());
        double quantite = Double.parseDouble(quantite_popup_ingredient_management.getText());
        String udm = combobox_udm_popup_ingredient_management.getValue();
        String source = source_popup_ingredient_management.getText();

        // Declare and initialize the nutrimentPrincipal variable
        String nutrimentPrincipal = "";

        if (chekbox_fibre_popup_ingredient_management.isSelected()) {
            nutrimentPrincipal = "Fibre";
        } else if (chekbox_energie_popup_ingredient_management.isSelected()) {
            nutrimentPrincipal = "Energie";
        } else if (chekbox_mineral_popup_ingredient_management.isSelected()) {
            nutrimentPrincipal = "Mineral";
        } else if (chekbox_protien_popup_ingredient_management.isSelected()) {
            nutrimentPrincipal = "Protien";
        }

        IngrediantEntity ingredient = new IngrediantEntity(nom, type, description, prix, quantite, udm, source, nutrimentPrincipal);

        // Create a confirmation dialog
        Alert confirmation = new Alert(AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Addition");
        confirmation.setHeaderText("Confirm Addition");
        confirmation.setContentText("Are you sure you want to add this ingredient?");

        // Add OK and Cancel buttons to the dialog
        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        confirmation.getButtonTypes().setAll(okButton, cancelButton);

        // Show the confirmation dialog and wait for user input
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == okButton) {
            // User confirmed the addition
            // Assuming that table_igredient_management is your TableView and
            // ingredientList is the ObservableList that backs it.
            ObservableList<IngrediantEntity> ingredientList = table_igredient_management.getItems();
            ingredientList.add(ingredient);

            // Assuming you have an ingredientService for adding ingredients
            ServiceIngredient ingredientService = new ServiceIngredient(Database.getInstance().getConnection());
            ingredientService.ajouter(ingredient);

            // Refresh the TableView to reflect the changes
            table_igredient_management.refresh();
        }
    }

    //display
    private void displaySelectedIngrediant(IngrediantEntity selectedIngrediant) {

        page_tableau_bord.setVisible(false);
        page_ingredient_main.setVisible(true);
        popup_ingredient_managment.setVisible(true);
        anchore_delete_modify_ingredient.setVisible(true);
        anchor_enregistrer_ingredient.setVisible(false);
        //
        anchor_valeur_nutritionnelles1.setVisible(true);
        anchor_valeur_nutritionnelles2.setVisible(false);
        //
        page_besoin_nutritionnels_management.setVisible(false);
        //
        popup_besoin_nutritionnels_management.setVisible(false);
        //
        anchor_besoin_nutritionnel.setVisible(false);
        anchor_valeur_nutritionnelles02.setVisible(false);
        anchor_btn_enregistrer_besoin_nutritionnel.setVisible(false);
        anchor_btn_modify_delete_besoin_nutritionnel.setVisible(false);
        //
        page_ration_managemnt.setVisible(false);
        //
        popup_ration_management.setVisible(false);
        //
        anchor_ration_management.setVisible(false);
        //
        page_animaux_management.setVisible(false);
        //
        popup_animal_management.setVisible(false);
        //
        anchor_animaux_management.setVisible(false);
        // ...

        // Populate your input fields with the selected data from the IngrediantEntity
        nom_ingredient_popup_ingredient_management.setText(selectedIngrediant.getNameIngredient());
        prix_popup_ingredient_management.setText(String.valueOf(selectedIngrediant.getCostIngredient()));
        quantite_popup_ingredient_management.setText(String.valueOf(selectedIngrediant.getItemQuantityIngredient()));
        source_popup_ingredient_management.setText(selectedIngrediant.getLoadedByIngredient());
        type_ingredient_popup_ingredient_management.setValue(selectedIngrediant.getTypeIngredient());
        description_popup_ingredient_management.setText(selectedIngrediant.getDescriptionIngredient());
        combobox_udm_popup_ingredient_management.setValue(selectedIngrediant.getUnitIngredient());
        // You might need to populate other fields or components as per your UI structure
    }

    //modifier
    @FXML
    private void modifySelectedIngredient() {
        // Get the selected IngrediantEntity from the table view
        IngrediantEntity selectedIngredient = table_igredient_management.getSelectionModel().getSelectedItem();

        if (selectedIngredient != null) {
            // Create a confirmation dialog
            Alert confirmation = new Alert(AlertType.CONFIRMATION);
            confirmation.setTitle("Confirm Modification");
            confirmation.setHeaderText("Confirm Modification");
            confirmation.setContentText("Are you sure you want to modify this ingredient?");

            // Add OK and Cancel buttons to the dialog
            ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            confirmation.getButtonTypes().setAll(okButton, cancelButton);

            // Show the confirmation dialog and wait for user input
            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.isPresent() && result.get() == okButton) {
                // User confirmed the modification
                // Get the updated values from your input fields
                String nameIngredient = nom_ingredient_popup_ingredient_management.getText();
                double costIngredient = Double.parseDouble(prix_popup_ingredient_management.getText());
                double itemQuantityIngredient = Double.parseDouble(quantite_popup_ingredient_management.getText());
                String descriptionIngredient = description_popup_ingredient_management.getText();
                String typeIngredient = type_ingredient_popup_ingredient_management.getValue();
                String unitIngredient = combobox_udm_popup_ingredient_management.getValue();
                String nutrimentPrincipalIngredient = "";

                // Check which checkboxes are selected
                if (chekbox_energie_popup_ingredient_management.isSelected()) {
                    nutrimentPrincipalIngredient = "Energie";
                } else if (chekbox_fibre_popup_ingredient_management.isSelected()) {
                    nutrimentPrincipalIngredient = "Fibre";
                } else if (chekbox_mineral_popup_ingredient_management.isSelected()) {
                    nutrimentPrincipalIngredient = "Mineral";
                } else if (chekbox_protien_popup_ingredient_management.isSelected()) {
                    nutrimentPrincipalIngredient = "Protien";
                }

                // Update the selectedIngredient
                selectedIngredient.setNameIngredient(nameIngredient);
                selectedIngredient.setCostIngredient(costIngredient);
                selectedIngredient.setItemQuantityIngredient(itemQuantityIngredient);
                selectedIngredient.setDescriptionIngredient(descriptionIngredient);
                selectedIngredient.setTypeIngredient(typeIngredient);
                selectedIngredient.setUnitIngredient(unitIngredient);
                selectedIngredient.setNutrimentPrincipalIngredient(nutrimentPrincipalIngredient);

                // Now, update the entity in your database
                ServiceIngredient service = new ServiceIngredient(Database.getInstance().getConnection());
                service.update(selectedIngredient);

                // Refresh the table view
                table_igredient_management.refresh();
            }
        } else {
            System.out.println("No item selected for modification.");
        }
    }

    //supp
    @FXML
    private void deleteSelectedIngredient() {
        // Get the selected IngrediantEntity from the table view
        IngrediantEntity selectedIngredient = table_igredient_management.getSelectionModel().getSelectedItem();

        if (selectedIngredient != null) {
            // Create a confirmation dialog
            Alert confirmation = new Alert(AlertType.CONFIRMATION);
            confirmation.setTitle("Confirm Deletion");
            confirmation.setHeaderText("Confirm Deletion");
            confirmation.setContentText("Are you sure you want to delete this ingredient?");

            // Add OK and Cancel buttons to the dialog
            ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
            confirmation.getButtonTypes().setAll(okButton, cancelButton);

            // Show the confirmation dialog and wait for user input
            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.isPresent() && result.get() == okButton) {
                // User confirmed the deletion
                int idIngredient = selectedIngredient.getIdIngredient(); // Replace this with the actual way to get the ID

                // Call the delete method of the service
                ServiceIngredient service = new ServiceIngredient(Database.getInstance().getConnection());
                service.supprimer(idIngredient);

                // Update the table after deletion
                table_igredient_management.getItems().remove(selectedIngredient);
            }
        } else {
            // No item selected, display an error message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to delete.");
            alert.showAndWait();
        }
    }

    // valeur nutritionnel 
    //ajout
    @FXML
    void addValeurNutritionnel(ActionEvent event) throws IOException {
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to add these Nutritional Values?");

        ButtonType confirmButtonType = new ButtonType("Yes");
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        confirmationAlert.getButtonTypes().setAll(confirmButtonType, cancelButtonType);

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == confirmButtonType) {
            // The user clicked "Yes," proceed with adding the nutritional values
            System.out.println("Database instance connection");
            System.out.println(Database.getInstance().getConnection());
            ServiceValeurNutritionnel serviceValeurNutritionnel = new ServiceValeurNutritionnel(Database.getInstance().getConnection());

            String pbText = pb_popup_ingredient_management.getText();
            String fbText = fb_popup_ingredient_management.getText();
            String adfText = adf_popup_ingredient_management.getText();
            String ndfText = ndf_popup_ingredient_management.getText();
            String msText = ms_popup_ingredient_management.getText();
            String ebText = eb_popup_ingredient_management.getText();
            String caText = ca_popup_ingredient_management.getText();
            String pText = p_popup_ingredient_management.getText();
            String mgText = mg_popup_ingredient_management.getText();
            String kText = k_popup_ingredient_management.getText();

            // Parse the text values to double
            double pb = Double.parseDouble(pbText);
            double fb = Double.parseDouble(fbText);
            double adf = Double.parseDouble(adfText);
            double ndf = Double.parseDouble(ndfText);
            double ms = Double.parseDouble(msText);
            double eb = Double.parseDouble(ebText);
            double ca = Double.parseDouble(caText);
            double p = Double.parseDouble(pText);
            double mg = Double.parseDouble(mgText);
            double k = Double.parseDouble(kText);

            // Create a NutritionalValues object
            ValeurNutritionnelEntity nutritionalValues = new ValeurNutritionnelEntity(pb, fb, adf, ndf, ms, eb, ca, p, mg, k);
            System.out.println(nutritionalValues);
            serviceValeurNutritionnel.ajouter(nutritionalValues);
        }
        // If the user clicked "Cancel" or the result is not present, do nothing.
    }

    //animaux 
    //display data of selected animal when clicking on any raw of the table 
    private void displaySelectedAnimal(Animal selectedAnimal) {
        // Hide other sections
        page_tableau_bord.setVisible(false);
        page_ingredient_main.setVisible(false);
        popup_ingredient_managment.setVisible(false);
        anchore_delete_modify_ingredient.setVisible(false);
        anchor_enregistrer_ingredient.setVisible(false);
        anchor_valeur_nutritionnelles1.setVisible(false);
        anchor_valeur_nutritionnelles2.setVisible(false);
        page_besoin_nutritionnels_management.setVisible(false);
        popup_besoin_nutritionnels_management.setVisible(false);
        anchor_besoin_nutritionnel.setVisible(false);
        anchor_valeur_nutritionnelles02.setVisible(false);
        anchor_btn_enregistrer_besoin_nutritionnel.setVisible(false);
        anchor_btn_modify_delete_besoin_nutritionnel.setVisible(false);
        page_ration_managemnt.setVisible(false);
        popup_ration_management.setVisible(false);
        anchor_ration_management.setVisible(false);

        // Show the animal management section
        page_animaux_management.setVisible(true);
        popup_animal_management.setVisible(true);
        anchor_animaux_management.setVisible(true);
        anchor_btn_enregistrer_animal.setVisible(false);
        anchor_btn_delete_modify_animal.setVisible(true);
        // Populate the animal management fields with data from selectedAnimal
        popup_combobox_unite_animal_amanagement1.setValue(selectedAnimal.getUnit_animal());

        popup_age_animal_management.setText(String.valueOf(selectedAnimal.getAge()));
        popup_combobox_espece_animal_amanagement.setValue(selectedAnimal.getEspece());  
        popup_sexe_animal_management.setText(selectedAnimal.getSexe());
        slide_poids_animal_management.setValue(selectedAnimal.getPoids());

        // You may need to update the above lines according to the actual properties in your Animal class.
    }

    //ajouter 
    @FXML
void addAnimal(ActionEvent event) {
    // Check if there is unsaved data
    if (dataNeedsSaving()) {
        Alert saveAlert = new Alert(AlertType.CONFIRMATION);
        saveAlert.setTitle("Unsaved Data");
        saveAlert.setHeaderText(null);
        saveAlert.setContentText("You have unsaved data. Do you want to save it?");

        ButtonType saveButtonType = new ButtonType("Save");
        ButtonType discardButtonType = new ButtonType("Discard");
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        saveAlert.getButtonTypes().setAll(saveButtonType, discardButtonType, cancelButtonType);

        Optional<ButtonType> result = saveAlert.showAndWait();

        if (result.get() == saveButtonType) {
            // Implement your save logic here
            saveData();
        } else if (result.get() == cancelButtonType) {
            return; // Cancel the operation
        }
    }

    // Continue with adding the new animal
    if (isInputValid()) {
        // Fields are not empty, proceed with adding the Animal
        System.out.println("Database instance connection");
        System.out.println(Database.getInstance().getConnection());
        ServiceAnimal animalService = new ServiceAnimal(Database.getInstance().getConnection());

        // Hide other sections
        page_tableau_bord.setVisible(false);
        page_ingredient_main.setVisible(false);
        popup_ingredient_managment.setVisible(false);
        anchor_valeur_nutritionnelles1.setVisible(false);
        anchor_valeur_nutritionnelles2.setVisible(false);
        page_besoin_nutritionnels_management.setVisible(false);
        popup_besoin_nutritionnels_management.setVisible(false);
        anchor_besoin_nutritionnel.setVisible(false);
        anchor_valeur_nutritionnelles02.setVisible(false);
        page_ration_managemnt.setVisible(false);
        popup_ration_management.setVisible(false);
        anchor_ration_management.setVisible(false);

        // Show the animal management section
        page_animaux_management.setVisible(true);
        popup_animal_management.setVisible(true);
        anchor_animaux_management.setVisible(true);
        anchor_btn_enregistrer_animal.setVisible(true);
        anchor_btn_delete_modify_animal.setVisible(false);

        // Create an Animal object and populate it with data from the input fields
        String espece = popup_combobox_espece_animal_amanagement.getValue();
        String sexe = popup_sexe_animal_management.getText();
        double poids = slide_poids_animal_management.getValue();
        int age = Integer.parseInt(popup_age_animal_management.getText());
        String unit_animal =popup_combobox_unite_animal_amanagement1.getValue();

        Animal animal = new Animal(espece, sexe, poids, age, unit_animal);

        ObservableList<Animal> animalList = table_anaimal_management.getItems();
        animalList.add(animal);

        animalService.ajouter(animal);
        table_anaimal_management.refresh();
    }
}

private boolean isInputValid() {
    // Validate that all required fields are not empty
    if (popup_combobox_espece_animal_amanagement.getValue() == null
            || popup_sexe_animal_management.getText().isEmpty()
            || popup_age_animal_management.getText().isEmpty()
            || popup_combobox_unite_animal_amanagement1.getValue().isEmpty()) {
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setTitle("Input Error");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Please fill in all the required fields.");
        errorAlert.showAndWait();
        return false;
    }
    return true;
}

    //modifier
    @FXML
    private void modifySelectedAnimal() {
        // Get the selected Animal from the table view
        Animal selectedAnimal = table_anaimal_management.getSelectionModel().getSelectedItem();

        if (selectedAnimal != null) {
            // Show a confirmation dialog
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to modify this animal?");

            ButtonType confirmButtonType = new ButtonType("Yes");
            ButtonType cancelButtonType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

            confirmationAlert.getButtonTypes().setAll(confirmButtonType, cancelButtonType);

            // Show the confirmation dialog and wait for the user's response
            Optional<ButtonType> result = confirmationAlert.showAndWait();

            if (result.get() == confirmButtonType) {
                // Get the updated values from your input fields
                String espece = popup_combobox_espece_animal_amanagement.getValue();
                String sexe = popup_sexe_animal_management.getText();
                double poids = slide_poids_animal_management.getValue();
                int age = Integer.parseInt(popup_age_animal_management.getText());
                String unit_animal =popup_combobox_unite_animal_amanagement1.getValue();

                // Update the selectedAnimal
                selectedAnimal.setEspece(espece);
                selectedAnimal.setSexe(sexe);
                selectedAnimal.setPoids(poids);
                selectedAnimal.setAge(age);
                selectedAnimal.setUnit_animal(unit_animal);

                // Now, update the entity in your database
                ServiceAnimal service = new ServiceAnimal(Database.getInstance().getConnection());
                service.update(selectedAnimal);

                // Refresh the table view
                table_anaimal_management.refresh();
            } else {
                System.out.println("Modification canceled.");
            }
        } else {
            // No item selected, display an error message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to modify.");
            alert.showAndWait();
        }
    }
//supp

    @FXML
    private void deleteSelectedAnimal() {
        // Get the selected Animal from the table view
        Animal selectedAnimal = table_anaimal_management.getSelectionModel().getSelectedItem();

        if (selectedAnimal != null) {
            // Show a confirmation dialog
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to delete this animal?");

            ButtonType confirmButtonType = new ButtonType("Yes");
            ButtonType cancelButtonType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

            confirmationAlert.getButtonTypes().setAll(confirmButtonType, cancelButtonType);

            // Show the confirmation dialog and wait for the user's response
            Optional<ButtonType> result = confirmationAlert.showAndWait();

            if (result.get() == confirmButtonType) {
                int idAnimal = selectedAnimal.getId(); // Replace this with the actual way to get the ID

                // Call the service's delete method
                ServiceAnimal service = new ServiceAnimal(Database.getInstance().getConnection());
                service.supprimer(idAnimal);

                // Update the table after deletion
                table_anaimal_management.getItems().remove(selectedAnimal);
            } else {
                System.out.println("Deletion canceled.");
            }
        } else {
            // No item selected, display an error message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to delete.");
            alert.showAndWait();
        }
    }

    //ration 
    //display 
    private void displaySelectedRation(Ration selectedRation) {
        // Hide other sections
        page_tableau_bord.setVisible(false);
        page_ingredient_main.setVisible(false);
        popup_ingredient_managment.setVisible(false);
        anchore_delete_modify_ingredient.setVisible(false);
        anchor_enregistrer_ingredient.setVisible(false);
        anchor_valeur_nutritionnelles1.setVisible(false);
        anchor_valeur_nutritionnelles2.setVisible(false);
        page_besoin_nutritionnels_management.setVisible(false);
        popup_besoin_nutritionnels_management.setVisible(false);
        anchor_besoin_nutritionnel.setVisible(false);
        anchor_valeur_nutritionnelles02.setVisible(false);
        anchor_btn_enregistrer_besoin_nutritionnel.setVisible(false);
        anchor_btn_modify_delete_besoin_nutritionnel.setVisible(false);
        page_animaux_management.setVisible(false);
        popup_animal_management.setVisible(false);
        anchor_animaux_management.setVisible(false);

        // Show the ration management section
        page_ration_managemnt.setVisible(true);
        popup_ration_management.setVisible(true);
        anchor_ration_management.setVisible(true);
        anchor_enregistrer_popup_ration.setVisible(false);
        anchor_delete_modifier_popup_ration.setVisible(true);
        // Populate the ration management fields with data from selectedRation
        status_combobox_ration_popup.setValue(selectedRation.getStatutRation());
        sexe_popup2_ration.setText(selectedRation.getSexeRation());
        popup2_poid_max_slider_ration.setValue(selectedRation.getPoidsMaxRation());
        popup2_poid_min_slider_ration.setValue(selectedRation.getPoidsMinRation());
        espece_combobox_ration.setValue(selectedRation.getEspeceRation());
        combobox_bute_production_popup2_ration.setValue(selectedRation.getButeProductionRation());
        combobox_popup_espece_ration.setValue(selectedRation.getEspeceRation());

        // You may need to update the above lines according to the actual properties in your Ration class.
    }

    //ajout 
    @FXML
void addRation(ActionEvent event) {
    // Validate input fields
    if (isInputValidRation()) {
        // Fields are not empty, proceed with adding the Ration
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to add this Ration?");

        ButtonType confirmButtonType = new ButtonType("Yes");
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        confirmationAlert.getButtonTypes().setAll(confirmButtonType, cancelButtonType);

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == confirmButtonType) {
            System.out.println("Database instance connection");
            System.out.println(Database.getInstance().getConnection());
            ServiceRation rationService = new ServiceRation(Database.getInstance().getConnection());

            // Hide other elements as needed (you can keep or remove as appropriate)
            page_tableau_bord.setVisible(false);
            page_ingredient_main.setVisible(false);
            popup_ingredient_managment.setVisible(false);
            anchor_valeur_nutritionnelles1.setVisible(false);
            anchor_valeur_nutritionnelles2.setVisible(false);
            page_besoin_nutritionnels_management.setVisible(false);
            popup_besoin_nutritionnels_management.setVisible(false);
            anchor_besoin_nutritionnel.setVisible(false);
            anchor_valeur_nutritionnelles02.setVisible(false);
            page_ration_managemnt.setVisible(true);
            popup_ration_management.setVisible(false);
            anchor_ration_management.setVisible(false);
            page_animaux_management.setVisible(false);
            popup_animal_management.setVisible(false);
            anchor_animaux_management.setVisible(false);

            // Create a Ration object and populate it with data from the input fields
            String especeRation = combobox_popup_espece_ration.getValue();
            String statutRation = status_combobox_ration_popup.getValue();
            String sexeRation = sexe_popup2_ration.getText();
            double poidsMinRation = popup2_poid_min_slider_ration.getValue();
            double poidsMaxRation = popup2_poid_max_slider_ration.getValue();
            String buteProductionRation = combobox_bute_production_popup2_ration.getValue();

            Ration ration = new Ration(especeRation, statutRation, sexeRation, poidsMinRation, poidsMaxRation, buteProductionRation);

            ObservableList<Ration> rationList = table_ration.getItems();
            rationList.add(ration);

            rationService.ajouter(ration);
            table_ration.refresh();
        }
        // If the user clicked "Cancel" or the result is not present, do nothing.
    }
}

private boolean isInputValidRation() {
    // Validate that all required fields are not empty
    if (combobox_popup_espece_ration.getValue() == null || status_combobox_ration_popup.getValue() == null
            || sexe_popup2_ration.getText().isEmpty() || combobox_bute_production_popup2_ration.getValue() == null) {
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setTitle("Input Error");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Please fill in all the required fields.");
        errorAlert.showAndWait();
        return false;
    }
    return true;
}


    //modifier 
    @FXML
    private void modifySelectedRation() {
        // Get the selected Ration from the table view
        Ration selectedRation = table_ration.getSelectionModel().getSelectedItem();

        if (selectedRation != null) {
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to modify this Ration?");

            ButtonType confirmButtonType = new ButtonType("Yes");
            ButtonType cancelButtonType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

            confirmationAlert.getButtonTypes().setAll(confirmButtonType, cancelButtonType);

            // Show the confirmation dialog and wait for the user's response
            Optional<ButtonType> result = confirmationAlert.showAndWait();

            if (result.isPresent() && result.get() == confirmButtonType) {
                // The user clicked "Yes", proceed with modification
                // Get the updated values from your input fields
                String espece = combobox_popup_espece_ration.getValue();
                String statut = status_combobox_ration_popup.getValue();
                String sexe = sexe_popup2_ration.getText();
                double poidsMin = popup2_poid_min_slider_ration.getValue();
                double poidsMax = popup2_poid_max_slider_ration.getValue();
                String buteProduction = combobox_bute_production_popup2_ration.getValue();

                // Update the selectedRation
                selectedRation.setEspeceRation(espece);
                selectedRation.setStatutRation(statut);
                selectedRation.setSexeRation(sexe);
                selectedRation.setPoidsMinRation(poidsMin);
                selectedRation.setPoidsMaxRation(poidsMax);
                selectedRation.setButeProductionRation(buteProduction);

                // Now, update the entity in your database
                ServiceRation service = new ServiceRation(Database.getInstance().getConnection());
                service.update(selectedRation);

                // Refresh the table view
                table_ration.refresh();
            } else {
                // The user clicked "Cancel" or the result is not present, do nothing.
            }
        } else {
            System.out.println("No item selected for modification.");
        }
    }

    //supprimer 
    @FXML
    private void deleteSelectedRation() {
        // Get the selected Ration from the table view
        Ration selectedRation = table_ration.getSelectionModel().getSelectedItem();

        if (selectedRation != null) {
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to delete this Ration?");

            ButtonType confirmButtonType = new ButtonType("Yes");
            ButtonType cancelButtonType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

            confirmationAlert.getButtonTypes().setAll(confirmButtonType, cancelButtonType);

            // Show the confirmation dialog and wait for the user's response
            Optional<ButtonType> result = confirmationAlert.showAndWait();

            if (result.isPresent() && result.get() == confirmButtonType) {
                // The user clicked "Yes," proceed with deletion
                int idRation = selectedRation.getIdRation();

                // Call the service's delete method
                ServiceRation service = new ServiceRation(Database.getInstance().getConnection());
                service.supprimer(idRation);

                // Update the table after deletion
                table_ration.getItems().remove(selectedRation);
            } else {
                // The user clicked "Cancel" or the result is not present, do nothing.
            }
        } else {
            // No item selected, display an error message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to delete.");
            alert.showAndWait();
        }
    }

    //oui ou non 
    private boolean unsavedChanges = false; // A flag to track unsaved changes

// Method to save data (implement this according to your data management logic)
    private void saveData() {
        // Implement your data saving logic here
        // Set the unsavedChanges flag to false once data is saved
        unsavedChanges = false;
    }

// Method to check if there are unsaved changes
    private boolean dataNeedsSaving() {
        if (unsavedChanges) {
            // Prompt the user to save or discard changes
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Unsaved Changes");
            alert.setHeaderText(null);
            alert.setContentText("You have unsaved changes. Do you want to save them?");

            ButtonType saveButton = new ButtonType("Save");
            ButtonType discardButton = new ButtonType("Discard");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(saveButton, discardButton, cancelButton);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == saveButton) {
                // User wants to save changes
                saveData();
            } else if (result.get() == discardButton) {
                // User wants to discard changes
                return false;
            } else {
                // User canceled the operation
                return true;
            }
        }
        return false;
    }
    private List<IngrediantEntity> ingredientList = new ArrayList<>();
private void createIngredientQuantityLineChart() throws SQLException {
    x.setLabel("Ingredient");
    y.setLabel("Quantity");

    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Ingredient Quantity");

    // Use your IngredientService to fetch data from the database
    ServiceIngredient ingredientService = new ServiceIngredient(Database.getInstance().getConnection());
    List<IngrediantEntity> ingredients = ingredientService.getAllIngredients();

    for (IngrediantEntity ingredient : ingredients) {
        series.getData().add(new XYChart.Data<>(ingredient.getNameIngredient(), ingredient.getItemQuantityIngredient()));
    }

    // Customize the tooltip for each data point
    for (XYChart.Data<String, Number> dataPoint : series.getData()) {
        String ingredientName = dataPoint.getXValue();
        double quantity = dataPoint.getYValue().doubleValue();
        Tooltip tooltip = new Tooltip(ingredientName + ": " + quantity);
        Tooltip.install(dataPoint.getNode(), tooltip);
    }

    ingredient_quantity.getData().clear(); // Clear previous data if any
    ingredient_quantity.getData().add(series);
}
private void createAnimalWeightLineChart() throws SQLException {
    x2.setLabel("Animal");
    y2.setLabel("Weight");

    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Animal Weight");

    // Use your AnimalWeightService to fetch data from the database
    ServiceAnimal animalService = new ServiceAnimal(Database.getInstance().getConnection());
    List<Animal> animals = animalService.getAllAnimal();

    for (Animal animal : animals) {
        series.getData().add(new XYChart.Data<>(animal.getEspece(), animal.getPoids()));
    }

    // Customize the tooltip for each data point
    for (XYChart.Data<String, Number> dataPoint : series.getData()) {
        String animalName = dataPoint.getXValue();
        double weight = dataPoint.getYValue().doubleValue();
        Tooltip tooltip = new Tooltip(animalName + ": " + weight + " kg");
        Tooltip.install(dataPoint.getNode(), tooltip);
    }

    linechart_animal.getData().clear(); // Clear previous data if any
    linechart_animal.getData().add(series);
}
///statics ?? 

    public void updateLabelWithTotalAnimalsInGestation() {
        ServiceAnimal animalService = new ServiceAnimal(Database.getInstance().getConnection());
    List<Animal> animals = animalService.getAllAnimal();
        int totalAnimalsInGestation = animalService.getTotalAnimalsInGestation();
        label_betails_en_gestation.setText("" + totalAnimalsInGestation);
        
    }


}
