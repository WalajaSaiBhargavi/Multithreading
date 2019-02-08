package DeadLocks;

public class Account {
    private int balance = 10000;
    public void withDraw(int amount){
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount){
        balance += amount;
    }
    public void transfer(Account toAccount, int amount){
        this.withDraw(amount);
        toAccount.deposit(amount);
    }
}
