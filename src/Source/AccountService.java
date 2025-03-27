package Source;

import model.Account;
import model.WalletSystem;

public interface AccountService {
    int searchForAccount(String username);
    boolean creatAccount(Account account);
    int login(Account account);

    void depositOrWithdraw(int indexOfAccount,double addedMoney,char op);
    double getBalance(int indexOfAccount);
    void showDetails(int indexOfAccount);

    void getAll();

}
