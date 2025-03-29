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

        boolean hasAccount = accountService.login(account);
        if (hasAccount) {
            System.out.println("Account login successfully");
            transactionPage(account);

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

    void transactionPage(Account loggedInAccount) {

        boolean exit = false;
        while (!exit) {
            System.out.println("a-deposit   b-withdraw   c-transfer   d-Show details   e-logout");
            String option = scanner.nextLine();

            switch (option) {
                case "a":
                    deposit(loggedInAccount);
                    break;
                case "b":
                    withdraw(loggedInAccount);
                    break;
                case "c":
                    //problem need to solve
                    transferTo(loggedInAccount);
                    break;
                case "d":
                    showDetails(loggedInAccount);
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

    private void transferTo(Account loggedInAccount) {
        System.out.println("------------>transfer<------------");
        System.out.println("Enter the username who get money");
        String ToUserName = scanner.nextLine();
        int validUser = accountService.searchForAccount(ToUserName);
        if (validUser != -1) {
            System.out.println("please enter amount to transfer:.");
            double transferMoney = getMoney();


            if (transferMoney != -1) {
                //if the deposit or withdraw return false that mean there is error with the transaction
                if (accountService.withdraw(loggedInAccount.getUserName(), transferMoney)
                        && accountService.deposit(ToUserName, transferMoney)) {
                    System.out.println("transaction is done");
                }
            }
        }
    }

    private void withdraw(Account loggedInAccount) {
        System.out.println("------------>withdraw<------------");
        System.out.println("please enter amount to withdraw.");

        double moneyFlow = getMoney();
        if (moneyFlow != -1) {
            accountService.withdraw(loggedInAccount.getUserName(), moneyFlow);
            System.out.println("trrrr trrr trrr : receive your money :)  trrr");
            System.out.println("your balance is " + loggedInAccount.getBalance());
        }

    }

    void deposit(Account loggedInAccount) {
        System.out.println("------------>Deposit<------------");
        System.out.println("please enter amount to deposit.");
        double moneyFlow = getMoney();
        if (moneyFlow != -1) {
            accountService.deposit(loggedInAccount.getUserName(), moneyFlow);
            System.out.println("money added successfully");
            System.out.println("your balance is " + loggedInAccount.getBalance());
        }
    }


    private double getMoney() {
        double money = 0;
        try {
            money = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("invalid input :(");
            scanner = new Scanner(System.in);
            return -1;
        }
        if (money < 0) {
            System.out.println("please enter positive number");
            return -1;
        }
        scanner.nextLine();
        return money;
    }


    private void showDetails(Account loggedInAccount) {
        accountService.showDetails(loggedInAccount.getUserName());
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
