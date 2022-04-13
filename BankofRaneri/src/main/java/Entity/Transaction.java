package Entity;

public class Transaction {
    private int id;
    private int userId;
    private int accountId;
    //Never finished for P0 but set up because i plan to tinker with this in the future as i suck less with coding this sort of thing
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
