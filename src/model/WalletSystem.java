package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WalletSystem {

    private final String walletName="IA wallet";


    private HashMap<String,Account> accounts=new HashMap();

    public String getWalletName() {
        return walletName;
    }


    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

}
