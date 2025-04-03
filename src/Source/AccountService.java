package Source;

import model.Account;
import model.WalletSystem;

public interface AccountService {
    boolean validUserName(String userName);
    boolean creatAccount(Account account);
    boolean login(Account account);

    boolean deposit(String loggedUser ,double addedMoney);
    boolean withdraw(String loggedUser,double addedMoney);

    double getBalance(String userName);
    void showDetails(String loggedUser);



}
