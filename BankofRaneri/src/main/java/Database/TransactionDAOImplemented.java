package Database;

import Entity.Transaction;
import Utilities.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class TransactionDAOImplemented implements TransactionDAO {

    @Override
    public Transaction createTransaction(Transaction transaction) {
        try{
            String sql = "insert into transaction values (default, ?,?)";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //Converts the first 3 strings the prepared statement encounters into Username,Password, and Account Type respectively
            ps.setInt(1, transaction.getUserId());
            ps.setInt(2, transaction.getAccountId());

            //Execute the statement/run it in SQL
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();//Forces the result set onto the first value since it defaults to a blank result before the first
            //Get int from the transaction_id column in DBeaver
            int generatedID = rs.getInt("transaction_id");
            transaction.setId(generatedID);
            return transaction;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Transaction getTransactionbyId(int id) {
        try{
            String sql = "select * from transaction where transaction_id = ?";
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //Converts the first 3 strings the prepared statement encounters into Username,Password, and Account Type respectively
            ps.setInt(1, id);


            //Execute the statement/run it in SQL
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();//Forces the result set onto the first value since it defaults to a blank result before the first
            //Get int from the transaction_id column in DBeaver

            //We've got the result from the database query but we need to return it in a form we can interact with
            Transaction transaction = new Transaction();
            transaction.setId(rs.getInt("transaction_id"));
            transaction.setUserId(rs.getInt("user_id"));
            transaction.setAccountId(rs.getInt("account_id"));
            return transaction;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public boolean deleteTransaction(int id) {
        return false;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }
}
