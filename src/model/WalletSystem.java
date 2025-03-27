package model;

import java.util.ArrayList;
import java.util.List;

public class WalletSystem {

    private final String walletName="IA wallet";
    private List<Account> accounts=new ArrayList<>();

    public String getWalletName() {
        return walletName;
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
