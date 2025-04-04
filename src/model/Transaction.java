package model;

public class Transaction {
    private String sender;
    private String receiver;
    private double transferredMoney;

    public Transaction() {
    }

    public Transaction(String sender, String receiver, double transferredMoney) {
        this.sender = sender;
        this.receiver = receiver;
        this.transferredMoney = transferredMoney;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public double getTransferredMoney() {
        return transferredMoney;
    }

    public void setTransferredMoney(double transferredMoney) {
        this.transferredMoney = transferredMoney;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", transferred Money=" + transferredMoney +
                '}';
    }
}
