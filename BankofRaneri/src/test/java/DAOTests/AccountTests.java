package DAOTests;

import Database.AccountDAO;
import Database.AccountDAOImplemented;
import Entity.Account;
import Entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTests {
    static AccountDAO accountDAO = new AccountDAOImplemented();
    @Test
    void createAccountTest(){
        Account TestAccount = new Account (0,0,1);
        Account SavedAccount = accountDAO.createAccount(TestAccount);



        //Assert that the id is not 0 (should never be 0, even if something does indeed go wrong/weird with the id number)
        Assertions.assertNotEquals(0, SavedAccount.getId());

    }
    @Test
    void  findAccountByIdTest(){
        Account searchAccount = accountDAO.getAccountbyId(4);
        System.out.println(searchAccount);
        Assertions.assertEquals(5000,searchAccount.getBalance());
    }

    @Test
    void deleteAccouontbyId(){
        boolean result = accountDAO.deleteAccount(6);
        Assertions.assertTrue(result);
    }
    @Test
    //Test created for updating amounts through UpdateBalance, in theory redundant but i am bad so this is how it is
    void updateAmounnt(){
        Account balanceToUpdate = accountDAO.getAccountbyId(2);
        System.out.println(balanceToUpdate.getBalance());
        accountDAO.updateBalance(balanceToUpdate,15000);
        System.out.println(balanceToUpdate.getBalance());
        //Assertion returns prior value/non-updated even though it does update in database
        Assertions.assertEquals(35000, balanceToUpdate.getBalance());
    }


}
