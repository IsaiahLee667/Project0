package Database;

import Entity.Account;
import Entity.User;
import Utilities.List;

public interface AccountDAO {
    //Create
    Account createAccount (Account account);
    //Read
    Account getAccountbyId (int id);

    //Update
    Account updateAccount (Account account);


    //Should be pointless update but I am bad
    Account updateBalance (Account account, double amount);

    //Delete
    Boolean deleteAccount (int id);

    List<Account> getAllUsers();

}
