package Database;

import Entity.User;
import Utilities.ConnectionUtil;
import Utilities.List;

import java.sql.*;

public class UserDAOImplemented implements UserDAO{
    @Override
    public User createUser(User user) {
        try{
            //Create a sql statement, inserting values into users table
            String sql = "insert into users values (default, ?,?,?)";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //Converts the first 3 strings the prepared statement encounters into Username,Password, and Account Type respectively
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getAccountType());

            //Execute the statement/run it in SQL
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();//Forces the
            //Get int from the id column in DBeaver
            int generatedID = rs.getInt("user_id");

            user.setId(generatedID);
            return user;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }

    @Override
    //Login?
    public User getUserById(int id) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select  * from users where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setAccountType(rs.getString("account_type"));


            System.out.println("Welcome back " + user.getUsername());
            return user;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public User updateUser(User user) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update users set username = ?, password = ?, account_type = ? where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getAccountType());
            ps.setInt(4,user.getId());
            ps.executeUpdate();
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteUserById(int id){
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "delete from users where user_id = ?";
            //Find user based on userid/primary key
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            //Return true if an account is found and deleted
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<User> getAllUsers() {

        return null;
    }
}
