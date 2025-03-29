package Source;

import model.Account;
import model.WalletSystem;

public interface AccountService {
    int searchForAccount(String username);
    boolean creatAccount(Account account);
    boolean login(Account account);

    boolean deposit(String loggedUser ,double addedMoney);
    boolean withdraw(String loggedUser,double addedMoney);

    double getBalance(int indexOfAccount);
    void showDetails(String loggedUser);

    void getAll();

}
