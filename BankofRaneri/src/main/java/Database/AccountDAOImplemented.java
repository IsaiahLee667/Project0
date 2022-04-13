package Database;

import Entity.Account;
import Entity.User;
import Utilities.ArrayList;
import Utilities.ConnectionUtil;
import Utilities.List;

import java.sql.*;

public class AccountDAOImplemented implements AccountDAO {


    @Override
    public Account createAccount(Account account) {
        try{
            String sql = "insert into account values (default, ?,?)";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //Converts the first 3 strings the prepared statement encounters into Username,Password, and Account Type respectively
            ps.setDouble(1,account.getBalance());
            ps.setInt(2, account.getOwnerid());

            //Execute the statement/run it in SQL
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();//Forces the
            //Get int from the id column in DBeaver
            int generatedID = rs.getInt("account_id");
            account.setId(generatedID);
            return account;




        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Account getAccountbyId(int id) {
        try{
            //Create Connection
            Connection conn = ConnectionUtil.createConnection();
            //Create Statement
            String sql = "select * from account where account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            //Change ? to give value
            ps.setInt(1,id);
            //Run Query
            ResultSet rs = ps.executeQuery();
            //Grab the next (first) value that was returned
            rs.next();
            Account account = new Account();
            account.setId(rs.getInt("account_id"));
            account.setBalance(rs.getInt("balance"));
            account.setOwnerid(rs.getInt("owner_id"));
            return account;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Account updateAccount(Account account) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update account set account_id = ?, balance = ? where owner_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,account.getId());
            ps.setDouble(2, account.getBalance());
            ps.setInt(3,account.getOwnerid());
            ps.executeUpdate();
            return account;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    //IN THEORY THIS DOES NOT NEED TO EXIST, I even talked about adam with this, but for some reason using UpdateAccount to change the balance was not working
    //So using jank that works, but I am aware this is awful/pointless if using the baseline crud functions properly.
    public Account updateBalance(Account account, double amount) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update account set balance = ? where account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            double updatedBalance = account.getBalance() + amount;
            account.setBalance(account.getBalance() +amount);
            //Add this statement because we also need to update THE LOCAL VALUE THAT WE PULLED IN JAVA
            ps.setDouble(1,updatedBalance);
            //This only sends back 35,000 to Dbeaver/Tells DBeaver to update the database, The account that we pulled in java is still unchanged
            ps.setInt(2,account.getId());
            ps.executeUpdate();
            return account;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteAccount(int id) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "delete from account where account_id = ?";
            //Search for accounts based on id
            //If one is found delete and return true
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Account> getAllUsers() {
        try{
            Connection conn = ConnectionUtil.createConnection();
            //will only grab one user at the moment
            String sql = "select * from account";
            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            List<Account> accounts = new ArrayList<>();
            //While there are still additional values in the result set query return
            while (rs.next()){
                //Create an account and fill it with stuff returned by the query
                Account account = new Account();
                account.setId(rs.getInt("account_id"));
                account.setBalance(rs.getDouble("balance"));
                account.setOwnerid(rs.getInt("owner_id"));
                accounts.add(account);

            }
            return accounts;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
