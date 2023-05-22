package Controller.Admin;

import Model.Account;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AccountsManagmentController implements Initializable {

    public static Account selectedAccountToUpdate;
    public static Stage updateStage;

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewAccountrBtn;
    @FXML
    private Button showAllAccountsBtn;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private Button searchAccountBtn;
    @FXML
    private TextField accontSearchTF;
    @FXML
    private TableView<Account> TableAccounts;
    @FXML
    private TableColumn<Account, Integer> id;
    @FXML
    private TableColumn<Account, Integer> acoountNumber;
    @FXML
    private TableColumn<Account, String> userName;
    @FXML
    private TableColumn<Account, String> currency;
    @FXML
    private TableColumn<Account, Double> balance;
    @FXML
    private TableColumn<Account, String> creationDate;
    @FXML
    private TableColumn<Account, Integer> userId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory("id"));
        acoountNumber.setCellValueFactory(new PropertyValueFactory("accountNumber"));
        userName.setCellValueFactory(new PropertyValueFactory("userName"));
        currency.setCellValueFactory(new PropertyValueFactory("currency"));
        balance.setCellValueFactory(new PropertyValueFactory("balance"));
        creationDate.setCellValueFactory(new PropertyValueFactory("creationData"));
        userId.setCellValueFactory(new PropertyValueFactory("userId"));

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
    private void showAccountCreationPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToCreateAccountsManagment();
    }

    @FXML
    private void showAllAccounts(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Account> usersList
                = (ObservableList<Account>) FXCollections.observableArrayList(Account.getAllAccounts());

        TableAccounts.setItems(usersList);
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) throws IOException {
        if (TableAccounts.getSelectionModel().getSelectedItem() != null) {
            selectedAccountToUpdate = TableAccounts.getSelectionModel().getSelectedItem();
            FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminFXML/UpdateAccountPage.fxml"));
            Parent rootUpdate = loaderUpdate.load();
            Scene updateUserScene = new Scene(rootUpdate);
            updateStage = new Stage();
            updateStage.setScene(updateUserScene);
            updateStage.setTitle("Update account " + selectedAccountToUpdate.getUserName());
            updateStage.show();
        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an account");
            warnAlert.setContentText("Please select an account from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) {
        if (TableAccounts.getSelectionModel().getSelectedItem() != null) {
            Account selectedAcount = TableAccounts.getSelectionModel().getSelectedItem();
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Account delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this account ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        selectedAcount.delete();
                    } catch (SQLException ex) {
                        Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("Account deleted");
                    deletedSuccessAlert.setContentText("Account deleted");
                    deletedSuccessAlert.show();
                }
            });

        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an account");
            warnAlert.setContentText("Please select an account from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void searchForAnAccount(ActionEvent event) {
    }

}
