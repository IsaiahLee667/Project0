package Services;

import Entity.Account;
import Utilities.List;

public interface AccountServices {



    //Show all accounts under the same id
    Account showAllAccounts (int ownerId);

    Account getAccount(int id);

    double displayBalance (int accountId);

    Account createAccount (Account account);

    Account makeDeposit (Account account, double amount);

    public List<Account> accountsOwnedByUser();


}


