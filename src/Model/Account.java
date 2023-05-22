package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Account {

    private int id;
    private int userId;
    private int accountNumber;
    private String userName;
    private String currency;
    private double balance;
    private String creationData;

    public Account(int userId, int accountNumber, String userName, String currency, double balance, String creationData) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.currency = currency;
        this.balance = balance;
        this.creationData = creationData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCreationData() {
        return creationData;
    }

    public void setCreationdata(String creationData) {
        this.creationData = creationData;
    }

    public int save() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO accounts (id,user_id,account_number,username,currency,balance,creation_date) VALUES (?, ?, ?, ?,?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setInt(2, this.getUserId());
        ps.setInt(3, this.getAccountNumber());
        ps.setString(4, this.getUserName());
        ps.setString(5, this.getCurrency());
        ps.setDouble(6, this.getBalance());
        ps.setString(7, this.getCreationData());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUserName()
                    + " Account was added successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public static ArrayList<Account> getAllAccounts() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Account account = new Account(rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7));
            account.setId(rs.getInt(1));
            accounts.add(account);

        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return accounts;
    }

    public int update() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "UPDATE accounts SET user_id=?, account_number=?, username=? , currency=?,balance=? , creation_date = ? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getUserId());
        ps.setInt(2, this.getAccountNumber());
        ps.setString(3, this.getUserName());
        ps.setString(4, this.getCurrency());
        ps.setDouble(5, this.getBalance());
        ps.setString(6, this.getCreationData());
        ps.setInt(7, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Account with id : " + this.getId() + " was updated successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public int delete() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "DELETE FROM accounts WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The account with id : " + this.getId() + " was deleted successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

}
