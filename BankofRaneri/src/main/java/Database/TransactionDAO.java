package Database;

import Entity.Transaction;

import java.util.List;

public interface TransactionDAO {
    //Create
    Transaction createTransaction (Transaction transaction);

    //Read
    Transaction getTransactionbyId(int id);

    //Update? Currently just meant to show a order of transactions
    Transaction updateTransaction(Transaction transaction);

    boolean deleteTransaction (int id);

    public List<Transaction> getAllTransactions();
}
