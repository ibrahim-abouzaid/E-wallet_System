package Source.ServiceController;

import Source.AccountService;
import model.Account;
import model.WalletSystem;


public class AccountServiceImplementation implements AccountService {

    private final WalletSystem walletSystem = new WalletSystem();

    @Override
    public int searchForAccount(String username) {
        int index = 0;
        for (Account ac : walletSystem.getAccounts()) {
            if (ac.getUserName().equals(username)) {
                return index;
            }
            index++;
        }
        if (index == walletSystem.getAccounts().size()) {
            index = -1;
        }
        return index;
    }

    @Override

    public boolean creatAccount(Account account) {
        int index = searchForAccount(account.getUserName());
        if (index==-1) {
            walletSystem.getAccounts().add(account);
            return true;
        }
        return false;
    }

    @Override
    public int login(Account account) {
        int index=searchForAccount(account.getUserName());
        if(index!=-1){
            if (walletSystem.getAccounts().get(index).getPassWord().equals(account.getPassWord())) {
                return index;
            }
            else {
                return -1;
            }
        }
           return index;
        }


    @Override
    public void depositOrWithdraw(int id, double money,char option) {

        double newBalance = 0;
        switch (option){
            case '+':
                newBalance=walletSystem.getAccounts().get(id).getBalance() + money;
                break;
            case '-':
                newBalance=walletSystem.getAccounts().get(id).getBalance() - money;
                break;
            default:
                System.out.println("invalid operation");
                break;
        }
            walletSystem.getAccounts().get(id).setBalance(newBalance);

    }

    @Override
    public double getBalance(int indexOfAccount) {
        return walletSystem.getAccounts().get(indexOfAccount).getBalance();

    }

    @Override
    public void showDetails(int indexOfAccount) {
        System.out.println(walletSystem.getAccounts().get(indexOfAccount).toString());
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
