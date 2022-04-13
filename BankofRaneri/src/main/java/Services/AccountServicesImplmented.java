package Services;

import Database.AccountDAO;
import Database.UserDAO;
import Entity.Account;
import Utilities.List;


public class AccountServicesImplmented implements AccountServices{

    private AccountDAO accountDAO;
    private UserDAO userDAO;

    public AccountServicesImplmented(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }


    @Override
    public Account showAllAccounts(int ownerId) {
        try{

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Account getAccount(int id) {
        return this.accountDAO.getAccountbyId(id);
    }

    @Override
    public double displayBalance(int accountId) {
        Account userAccount = this.accountDAO.getAccountbyId(accountId);
        return userAccount.getBalance();
    }

    @Override
    public Account createAccount(Account account) {
        return this.accountDAO.createAccount(account);
    }

    @Override
    //There is for sure a less jank way to do this, but it worked and I accept all judgment for using this to weld together sucky code.
    public Account makeDeposit(Account account, double amount) {

         account.setBalance(account.getBalance() + amount);
         accountDAO.updateAccount(account);
         return account;

    }



    @Override
    public List<Account> accountsOwnedByUser() {
        return this.accountDAO.getAllUsers();
    }


}
