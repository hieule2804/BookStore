/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Book;

/**
 *
 * @author ADMIN
 */
public class AccountDAO extends DBContext {

    //get list account
    public List<Account> getListAccount() {
        List<Account> list = new ArrayList<>();
        connection = getConnection();
        String sql = "select * from Account";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Date date = resultSet.getDate("date");
                boolean gender = resultSet.getBoolean("gender");
                int role = resultSet.getInt("role");
                Account a = new Account();
                a.setDate(date);
                a.setGender(gender);
                a.setPassword(password);
                a.setRole(role);
                a.setUsername(username);
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Account checkAccount(String username, String password) {
        AccountDAO dao = new AccountDAO();
        for (Account account : dao.getListAccount()) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }


    public boolean checkDuplicateUsername(String username) {
        boolean check = false;
        AccountDAO dao = new AccountDAO();
        for (Account account : dao.getListAccount()) {
            if (account.getUsername().equals(username)) {
                check = true;
            }
        }
        return check;
    }

    public void addToDB(String usernameRe, String passwordRe, Date date1, boolean gender) {
        connection = getConnection();
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[date]\n"
                + "           ,[gender]\n"
                + "           ,[role])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, usernameRe);
            statement.setString(2, passwordRe);
            statement.setDate(3, date1);
            statement.setBoolean(4, gender);
            statement.setInt(5, 3);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addToDB(String usernameRe, String passwordRe, Date date, boolean gender, int role) {
        connection = getConnection();
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[date]\n"
                + "           ,[gender]\n"
                + "           ,[role])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, usernameRe);
            statement.setString(2, passwordRe);
            statement.setDate(3, date);
            statement.setBoolean(4, gender);
            statement.setInt(5, role);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleAcc(String userDE) {
        connection = getConnection();
        String sql = "DELETE FROM [dbo].[Account]\n"
                + "      WHERE username =  ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, userDE);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Account> getListAccountByName(String nameSearch) {
        List<Account> list = new ArrayList<>();
        //connect db
        connection = getConnection();
        String sql = "SELECT *\n"
                + "  FROM Account\n"
                + "   where username like ?;";
        try {
            //prepare command
            statement = connection.prepareStatement(sql);
            statement.setString(1, nameSearch + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Date date = resultSet.getDate("date");
                boolean gender = resultSet.getBoolean("gender");
                int role = resultSet.getInt("role");
                Account a = new Account();
                a.setDate(date);
                a.setGender(gender);
                a.setPassword(password);
                a.setRole(role);
                a.setUsername(username);
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(bookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void updateAccount(String usernameUD, String passwordUD, Date date, boolean genderUD, int roleUD) {
        connection = getConnection();
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [username] = ?\n"
                + "      ,[password] =?\n"
                + "      ,[date] = ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[role] = ?\n"
                + " WHERE [username] like ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, usernameUD);
            statement.setString(2, passwordUD);
            statement.setDate(3, date);
            statement.setBoolean(4, genderUD);
            statement.setInt(5, roleUD);
            statement.setString(6, usernameUD);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean checkOldPass(String username, String pass) {
        boolean check = false;
        for (Account account : getListAccount()) {
            if (account.getUsername().equals(username) && account.getPassword().equals(pass)) {
                check = true;
            }
        }
        return check;
    }

    public void changePass(String user, String new_pass) {
        connection = getConnection();
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [password] = ?\n"
                + "      \n"
                + " WHERE username like ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, new_pass);
            statement.setString(2, user);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Account> getListShipper() {
          List<Account> list = new ArrayList<>();
          for (Account account : getListAccount()) {
            if(account.getRole()==1)
            {list.add(account);
            }
        }
          return list;
    }
    
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        for (Account account : dao.getListShipper()) {
            System.out.println(account);
        }
    }
}
