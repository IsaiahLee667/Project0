package Entity;

public class Transaction {
    private int id;
    private int userId;
    private int accountId;

    public Transaction(){

    }

    public Transaction(int id, int userId, int accountId) {
        this.id = id;
        this.userId = userId;
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
