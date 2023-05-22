/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Admin;

import Model.Account;
import Model.User;
import View.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dev Subhe Daher <your.name at your.org>
 */
public class CreateAccountController implements Initializable {

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private TextField balanceTF;
    @FXML
    private TextField currencyTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button saveNewAccountBtn;
    @FXML
    private TextField accountNumberTF1;
    @FXML
    private TextField creationDateF1;
    @FXML
    private TextField userIdF1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void cancelUserCreation(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAccountsManagment();
    }

    @FXML
    private void saveNewAccountBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        // get data from all text fields 
        int userId = Integer.parseInt(userIdF1.getText());
        int accountNumber = Integer.parseInt(accountNumberTF1.getText());
        String username = usernameTF.getText();
        String currency = currencyTF.getText();
        double balance = Double.parseDouble(balanceTF.getText());
        String creationDate = creationDateF1.getText();
        // make an user object having this data
        Account account = new Account(userId, accountNumber, username, currency, balance, creationDate);
        // save the user in database by save method
        account.save();
        //after saving should return to the back scene and show an alert
        ViewManager.adminPage.changeSceneToAccountsManagment();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account inserted");
        alert.setContentText("Account inserted");
        alert.showAndWait();
    }

}
