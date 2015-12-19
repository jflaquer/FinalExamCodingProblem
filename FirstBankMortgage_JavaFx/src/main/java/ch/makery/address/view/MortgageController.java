package ch.makery.address.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.UUID;

import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;


public class MortgageController {
	@FXML
	private TextField In;
	
	@FXML
	private TextField Expense;
	
	@FXML
	private TextField Credit_score;
	
	@FXML
	private TextField Cost_of_house;
	
	
	@FXML
	private Label finalMortgage;
	
	@FXML
	private ComboBox years;
	ObservableList<Integer> year = FXCollections.observableArrayList(30, 15);
	
	@FXML
	private void HandleMortgage(){
		double rate = RateDAL.getRate(Integer.parseInt(Credit_score.getText()));
		double calculatedMortgage = RateDAL.MortageCalc(rate, (int) years.getSelectionModel().getSelectedItem(), Double.parseDouble(Cost_of_house.getText()), false, 0);
		if (calculatedMortgage <= Integer.parseInt(In.getText())*36/100 & calculatedMortgage <= (Integer.parseInt(In.getText())+Integer.parseInt(Expense.getText())*18/100)){
			finalMortgage.setText(Double.toString(calculatedMortgage));
		} else {
			finalMortgage.setText("Not qualified");
		}
		finalMortgage.setVisible(true);
	}
	

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MortgageController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	years.setItems(year);
    	finalMortgage.setVisible(false);
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
   
}