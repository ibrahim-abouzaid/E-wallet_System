package Source.ServiceController;

import Source.AccountService;
import Source.AppService;
import Source.DataValidation;
import model.Account;
import model.WalletSystem;

import java.util.Scanner;

public class AppServiceImplementaion implements AppService {
    WalletSystem walletSystem = new WalletSystem();
    AccountService accountService = new AccountServiceImplementation();
    DataValidation validation = new DataValidationImplementation();

    Scanner scanner = new Scanner(System.in);
    int choice, counter = 4;

    @Override
    public void runApp() {
        boolean exit = false;
        System.out.println("---------->Welcome to " + walletSystem.getWalletName() + "<----------");
        options(exit);
    }

    void login() {
//        DataValidationImplementation
        System.out.println("------>Login Account<------");
        Account account = accountDetails();

        int hasAccount = accountService.login(account);
        if (hasAccount != -1) {
            System.out.println("Account login successfully");
            mainPage(hasAccount);

        } else {
            System.out.println("Error: incorrect username or password.");
        }
    }

    void createAccount() {

        System.out.println("------>Create Account<------");
        Account account = accountDetails();
        if (!validation.validateUserName(account.getUserName())) {
            System.out.println("error username must be more than or equal to 3 and the first character is uppercase");
        } else if (!validation.validatePassword((account.getPassWord()))) {
            System.out.println("error password size must be more than or equal to 6 and has at lest one lowercase,uppercase,number and special character");
        } else {
            boolean isCreated = accountService.creatAccount(account);
            if (isCreated) {
                System.out.println("Account created successfully");
            } else {
                System.out.println("Error: this account is used try another username.");
            }
        }

    }

    void mainPage(int indexOfAccount) {

        boolean exit = false;
        while (!exit) {
            System.out.println("a-deposit   b-withdraw   c-transfer   d-Show details   e-logout");
            String option = scanner.nextLine();

            switch (option) {
                case "a":
                    deposit(indexOfAccount);
                    break;
                case "b":
                    withdraw(indexOfAccount);
                    break;
                case "c":
                    //problem need to solve
                    transferTo(indexOfAccount);
                    break;
                case "d":
                    showDetails(indexOfAccount);
                    break;
                case "e":
                    System.out.println("logout");
                    exit = true;
                    break;
                default:
                    System.out.println("invalid input");
                    break;

            }

        }
    }

    private void transferTo(int indexOfThisAccount) {
        System.out.println("------------>transfer<------------");
        System.out.println("Enter the username who get money");
        String ToUserName = scanner.nextLine();
        int indexOfOtherUser = accountService.searchForAccount(ToUserName);
        if (indexOfOtherUser != -1) {
            System.out.println("please enter amount to transfer:.");
            double transferMoney = getMoney();

            //check current user balance
            if (accountService.getBalance(indexOfThisAccount) < transferMoney) {
                System.out.println("sorry no enough money in your account");
            } else {
                accountService.depositOrWithdraw(indexOfThisAccount, transferMoney, '-');
                accountService.depositOrWithdraw(indexOfOtherUser, transferMoney, '+');
                System.out.println("transaction is done");

            }
        } else {
            System.out.println("error this username not found");
        }
    }

    private void withdraw(int indexOfThisAccount) {
        System.out.println("------------>withdraw<------------");
        System.out.println("please enter amount to withdraw.");

        double moneyFlow = getMoney();

        if ( accountService.getBalance(indexOfThisAccount) < moneyFlow) {
            System.out.println("error no enough money in your account :(");
        } else if(moneyFlow!=0.0){
            accountService.depositOrWithdraw(indexOfThisAccount, moneyFlow, '-');
            System.out.println("trrrr trrr trrr : receive your money :)  trrr");
            System.out.println("your balance is " + accountService.getBalance(indexOfThisAccount));
        }
//        scanner.nextLine();

    }

    void deposit(int indexOfAccount) {
        System.out.println("------------>Deposit<------------");
        System.out.println("please enter amount to deposit.");
        double moneyFlow = getMoney();
        if(moneyFlow!=0.0){
        accountService.depositOrWithdraw(indexOfAccount, moneyFlow, '+');
        System.out.println("money added successfully");
        System.out.println("your balance is " + accountService.getBalance(indexOfAccount));
        }
    }


    private double getMoney() {
        double money = 0;
        try {
            money = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("invalid input :(");
            scanner = new Scanner(System.in);
            return 0.0;
        }
        if (money < 0) {
            System.out.println("please enter positive number");
            return 0.0;
        }
        scanner.nextLine();
        return money;
    }


    private void showDetails(int indexOfAccount) {
        accountService.showDetails(indexOfAccount);
    }


    void options(boolean exit) {
        while (!exit) {
            System.out.println("1-Login  2-Create Account  3-Exit");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("invalid option");
                scanner = new Scanner(System.in);
                continue;
            }
            switch (choice) {
                case 1 -> login();
                case 2 -> createAccount();
                case 3 -> {
                    System.out.println("thank you for using " + walletSystem.getWalletName());
                    exit = true;
                }
                default -> {
                    System.out.println("Invalid Option please try again you have " + --counter + " tries left");
                    if (counter == 0) {
                        System.out.println("thank you for using " + walletSystem.getWalletName());
                        exit = true;

                    }

                }
            }
        }
    }

    Account accountDetails() {
        System.out.println("please enter user name.");
        String userName = scanner.nextLine();
        System.out.println("please enter password");
        String passWord = scanner.nextLine();
        return new Account(userName, passWord);
    }
}
