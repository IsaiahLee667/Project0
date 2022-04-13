package Database;

import Entity.User;
import Utilities.List;

public interface UserDAO {
    //Create
    User createUser(User user);

    //Read
    User getUserById(int id);

    //Update
    User updateUser(User user);

    //Delete
     boolean deleteUserById(int id);

     List<User> getAllUsers();



    //UserDAO used to have balance functions out of "user logins to account" and to be honest
    // this is more to do with the fact my account is so barebones at this point, it probably is better just to have it as 1 database

}
