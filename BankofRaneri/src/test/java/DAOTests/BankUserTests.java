package DAOTests;

import Database.UserDAO;
import Database.UserDAOImplemented;
import Entity.Account;
import Entity.User;
import Services.UserServices;
import Services.UserServicesImplemented;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankUserTests {
    static UserDAO userDAO = new UserDAOImplemented();
    static UserServices userService = new UserServicesImplemented();

    @Test
    void createUserTest(){
        User TestUser = new User(0,"newtest","password","Checking");

        User SavedUser = userDAO.createUser(TestUser);

        //Assert that the id is not 0 (should never be 0, even if something does indeed go wrong/weird with the id number)
        Assertions.assertNotEquals(0, SavedUser.getId());

    }
    @Test
    void logintoUserAcount(){
        User user = userDAO.getUserById(2);
        System.out.println(user.getId());
        Assertions.assertEquals("asdf", user.getUsername());
        //Name of user was returned properly with proper username and password

        //Message of "No one was found was printed with improper username and password
    }
    @Test
    void deleteaccount(){
        boolean result = userDAO.deleteUserById(4);
        //Successfully deleted user in the database with id 4
        Assertions.assertTrue(result);
    }

    @Test
    void updateAccount(){
        User usertoUpdate = userDAO.getUserById(13);
        usertoUpdate.setUsername("Userwasupdated");
        userDAO.updateUser(usertoUpdate);
        User updateduser =userDAO.getUserById(13);
        Assertions.assertEquals("Userwasupdated",updateduser.getUsername());



    }

    @Test
    void loginAccount(){

        User testUser = userService.loginCheck("UpdateUser", "UpdatePassword");
        System.out.println(testUser);
        Assertions.assertEquals("UpdateUser", testUser.getUsername());
    }
}
