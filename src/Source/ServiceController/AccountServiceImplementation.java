package Source.ServiceController;

import Source.AccountService;
import model.Account;
import model.WalletSystem;


public class AccountServiceImplementation implements AccountService {

    private final WalletSystem walletSystem = new WalletSystem();

    @Override
    public int searchForAccount(String username) {
        int index=0;
        for (Account ac : walletSystem.getAccounts()) {
            if (ac.getUserName().equals(username)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override

    public boolean creatAccount(Account account) {
        int hasAccount = searchForAccount(account.getUserName());
        if (hasAccount==-1) {
            walletSystem.getAccounts().add(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean login(Account loginAccount) {
       int indexOfAccount= searchForAccount(loginAccount.getUserName());
           if(indexOfAccount!=-1){
               if(walletSystem.getAccounts().get(indexOfAccount).getPassWord().equals(loginAccount.getPassWord())){
                   return true;
               }
           }
           return false;
        }


    @Override
    public boolean deposit(String loggedInUserName, double money) {
        int indexOfAccount=searchForAccount(loggedInUserName);
        if(indexOfAccount!=-1){
            walletSystem.getAccounts().get(indexOfAccount).setBalance(
                    walletSystem.getAccounts().get(indexOfAccount).getBalance()+money);
            return true;
        } else{
            System.out.println("Error: this account may not registered");
        }
        return false;


    }

    @Override
    public boolean withdraw(String loggedInUserName, double money) {
        int  indexOfAccount=searchForAccount(loggedInUserName);
        if(indexOfAccount!=-1){
            if(walletSystem.getAccounts().get(indexOfAccount).getBalance()>=money){
            walletSystem.getAccounts().get(indexOfAccount).setBalance(
                    walletSystem.getAccounts().get(indexOfAccount).getBalance()-money);
            return true;
        }
            else {
                System.out.println("Error: no enough balance in your account");
            }
        }
        else {
            System.out.println("Error: this account may not registered");
        }
        return false;

    }

    @Override
    public double getBalance(int indexOfAccount) {
        return walletSystem.getAccounts().get(indexOfAccount).getBalance();

    }

    @Override
    public void showDetails(String userName) {
        int indexOfUsername=searchForAccount(userName);
        System.out.println(walletSystem.getAccounts().get(indexOfUsername).toString());
    }


    @Override
    public void getAll() {
        for (Account ac : walletSystem.getAccounts()) {
            System.out.println(ac.getUserName());
            System.out.println(ac.getPassWord());
            System.out.println("****************");

        }
    }

}
