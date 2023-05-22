package Controller.Admin;

import Model.Account;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UpdateAccountPageController implements Initializable {

    private Account oldAccount;

    @FXML
    private TextField usernameTF;
    @FXML
    private TextField currencyTF;
    @FXML
    private TextField balanceTF;
    @FXML
    private Button updateUserBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField userIdTF1;
    @FXML
    private TextField accountNumberTF11;
    @FXML
    private TextField creationDateTF1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.oldAccount = Controller.Admin.AccountsManagmentController.selectedAccountToUpdate;
        userIdTF1.setText(oldAccount.getUserId() + "");
        accountNumberTF11.setText(oldAccount.getAccountNumber() + "");
        usernameTF.setText(oldAccount.getUserName());
        currencyTF.setText(oldAccount.getCurrency());
        balanceTF.setText(oldAccount.getBalance() + "");
        creationDateTF1.setText(oldAccount.getCreationData());
    }

    @FXML
    private void updateUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        int userId = Integer.parseInt(userIdTF1.getText());
        int accountNumber = Integer.parseInt(accountNumberTF11.getText());
        String userName = usernameTF.getText();
        String currency = currencyTF.getText();
        double balance = Double.parseDouble(balanceTF.getText());
        String creationDate = creationDateTF1.getText();
        Account newAccount = new Account(userId, accountNumber, userName, currency, balance, creationDate);
        newAccount.setId(oldAccount.getId());
        newAccount.update();
        Controller.Admin.AccountsManagmentController.updateStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account updated");
        alert.setContentText("Account updated");
        alert.showAndWait();
    }

    @FXML
    private void cancelCreation(ActionEvent event) {
        Controller.Admin.AccountsManagmentController.updateStage.close();
    }

}
