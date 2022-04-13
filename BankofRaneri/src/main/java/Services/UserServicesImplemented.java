package Services;

import Database.AccountDAO;
import Database.UserDAO;
import Database.UserDAOImplemented;
import Entity.Account;
import Entity.User;
import Utilities.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserServicesImplemented implements UserServices {
    private AccountDAO accountDAO;
    private UserDAO userDAO;

    public UserServicesImplemented(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public UserServicesImplemented() {

    }

    @Override
    public User loginCheck(String username, String password) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            //Search for a user with the given username and password. values are set to unique so no risk of dupes
            String sql = "select  * from users where username = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            //This was working before, and then night of the 12th, started causing problems for some reason so commented out
           /* if (rs.next() == false){
                System.out.println("No user was found with that username and password, please try again");
                return null;
            }*/
            rs.next();
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setAccountType(rs.getString("account_type"));
            System.out.println("Welcome back " + username);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when searching for account, please try again");
            return null;
        }
    }

    @Override
    public User createAccount(User user) {
        return this.userDAO.createUser(user);
        //Account accountFromUser = new Account(0,0, user.getId());

    }


}
