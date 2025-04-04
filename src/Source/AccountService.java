package Source;

import model.Account;
import model.Transaction;
import model.WalletSystem;

import java.util.List;

public interface AccountService {
    boolean validUserName(String userName);
    boolean creatAccount(Account account);
    boolean login(Account account);

    boolean deposit(String loggedUser ,double addedMoney);
    boolean withdraw(String loggedUser,double addedMoney);

    double getBalance(String userName);
    void showDetails(String loggedUser);
    List<Transaction> getTransactions(String loginUser);
    void setTransaction(String fromUser,String toUser,double money);

    void demoData();



}
