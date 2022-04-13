package Entity;

public class Account {
    private int id;
    private double balance;
    private int ownerid;


    public Account(){
        //In case of weird construction, Account is 0 by default.
        this.balance = 0;
    }

    public Account(int id, double balance, int ownerid) {
        this.id = id;
        this.balance = balance;
        this.ownerid = ownerid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(int ownerid) {
        this.ownerid = ownerid;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance='" + balance + '\'' +
                ", ownerId='" + ownerid + '}';
    }
}
