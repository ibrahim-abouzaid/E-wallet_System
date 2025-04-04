package Source.ServiceController;

import Source.AccountService;
import model.Account;
import model.Transaction;
import model.WalletSystem;

import java.util.List;


public class AccountServiceImplementation implements AccountService {

    private final WalletSystem walletSystem = new WalletSystem();




    @Override
    public boolean validUserName(String userName) {
        if(walletSystem.getAccounts().get(userName)!=null){
            return true;
        }
        return false;
    }

    @Override

    public boolean creatAccount(Account account) {
        if (walletSystem.getAccounts().get(account.getUserName())==null) {
            walletSystem.getAccounts().put(account.getUserName(), account);
            return true;
        }
        return false;
    }

    @Override
    public boolean login(Account loginAccount) {
           if(walletSystem.getAccounts().get(loginAccount.getUserName())!=null){
               if(walletSystem.getAccounts().get(loginAccount.getUserName()).getPassWord().equals(loginAccount.getPassWord())){
                   return true;
               }
           }
           return false;
        }


    @Override
    public boolean deposit(String loggedInUserName, double money) {

        if( walletSystem.getAccounts().get(loggedInUserName)!=null){
            walletSystem.getAccounts().get(loggedInUserName).setBalance(
                    walletSystem.getAccounts().get(loggedInUserName).getBalance()+money);
            return true;
        } else{
            System.out.println("Error: this account may not registered");
        }
        return false;


    }

    @Override
    public boolean withdraw(String loggedInUserName, double money) {
        if(walletSystem.getAccounts().get(loggedInUserName)!=null){
            if(walletSystem.getAccounts().get(loggedInUserName).getBalance()>=money){
                walletSystem.getAccounts().get(loggedInUserName).setBalance(
                        walletSystem.getAccounts().get(loggedInUserName).getBalance()-money);
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
    public double getBalance(String userName) {
        return walletSystem.getAccounts().get(userName).getBalance();

    }

    @Override
    public void showDetails(String userName) {
        System.out.println(walletSystem.getAccounts().get(userName).toString());
    }

    @Override
    public List<Transaction> getTransactions(String userName) {
        return walletSystem.getAccounts().get(userName).getTransactionList();

    }

    @Override
    public void setTransaction(String fromUser,String toUserName, double money) {
        walletSystem.getAccounts().get(fromUser).getTransactionList().add(new Transaction(fromUser,toUserName,money));
    }

    @Override
    public void demoData() {
        walletSystem.getAccounts().put("Ali", new Account("Ali","Ali12#"));
        walletSystem.getAccounts().put("Ali2", new Account("Ali2","Ali12#"));
    }


}
