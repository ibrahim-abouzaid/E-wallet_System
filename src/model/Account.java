package model;

public class Account {
    private String userName;
    private String passWord;
    private boolean active;
    private double balance;

    public Account() {

    }

    public Account(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.active=true;
        this.balance=0;


    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
