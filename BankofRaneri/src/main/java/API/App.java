package API;

import Database.AccountDAO;
import Database.AccountDAOImplemented;
import Database.UserDAOImplemented;
import Entity.Account;
import Entity.User;
import Services.AccountServices;
import Services.AccountServicesImplmented;
import Services.UserServices;
import Services.UserServicesImplemented;
import Utilities.List;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static UserServices userServices = new UserServicesImplemented(new UserDAOImplemented());
    public static AccountServices accountServices = new AccountServicesImplmented(new AccountDAOImplemented());
    public static Scanner scanner = new Scanner(System.in);
    //Spitballing ideas to "save the user" that is currently logged in, with how unpolished everything is, staying theoretical
    private User currentUser;
    private Account currentAccount;
    public static void main(String[] args) {
        startScreen();


    }

    public static void startScreen(){

        System.out.println("Welcome to Bank of Raneri! Press the following numbers to do the relative action");
        System.out.println("1.Create User \n2.Login to User Account \n3.Exit Application");
        int selection = scanner.nextInt();

        switch(selection){
            case 1:{
                //Accepting values into CreateUser Function
                System.out.println("Create Username");
                String username = scanner.next();
                System.out.println("Create Password");
                String password = scanner.next();
                System.out.println("Enter Account Type");
                String accountType = scanner.next();
                User user = new User (0, username, password, accountType);
                App.userServices.createAccount(user);
                Account account = new Account (0, 0, user.getId());
                App.accountServices.createAccount(account);
                System.out.println("Account Created");
                //Relaunch menu, since most likely after creating account, the user might want to login, but if not, just press 3 to quit out
                startScreen();
            }
            case 2:{
                System.out.println("Enter username");
                String username = scanner.next();
                System.out.println("Enter password");
                String password = scanner.next();
                User newUser = new User();
                newUser = userServices.loginCheck(username, password);
                if (newUser != null){


                    loggedIn(newUser);

                }
                else{
                    //Was a sysout before, but put statement into logincheck method, now just takes you back to start
                    break;

                }




            }
            case 3:{
                System.out.println("Thank you for using Bank of Raneri!");
                System.exit(0);

            }
            default:{
                System.out.println("Invalid option, please try again");
                startScreen();
            }


        }
    }

    public static void loggedIn(User user){
        System.out.println("Welcome " + user.getUsername() + ", Press the following to do the relative action");
        System.out.println("1.View Balance \n2.Make a Deposit \n3.Make a Withdrawal \n4.View All Accounts");
        //Since we have a user enter their credentials, just doing a get account by id function at the start, does age poorly though
        Account userAccount = App.accountServices.getAccount(user.getId());
        //Forced to save the user in order to relaunch loggedIn as a  "restart in case of mistype"
        User currentUser = user;
        int selection = scanner.nextInt();

        switch(selection){
            case 1:{
                double balance = userAccount.getBalance();
                System.out.println("Your balance is " + balance);
                break;
            }
            case 2:{
                System.out.println("Input the amount you would like to deposit");
                double amount = scanner.nextDouble();

                App.accountServices.makeDeposit(userAccount,amount);
                System.out.println(amount + " has been deposited into your account, your balance is now " + userAccount.getBalance()) ;
            }
            case 3:{
                System.out.println("Input the amount you would like to deposit");
                double amount = scanner.nextDouble();
                //Check to prevent overdrawing
                if (amount > userAccount.getBalance()){
                    System.out.println("Amount is larger then what is in your account, your current balance is " + userAccount.getBalance());

                }
                else{
                    //UpdateBalance function but just adding a negative number instead
                  double amountToWithdraw = amount * -1;
                  App.accountServices.makeDeposit(userAccount, amountToWithdraw);
                    System.out.println(amountToWithdraw + " has been withdrawn from your account, your balance is now "+ userAccount.getBalance());
                }


            }
            case 4:{
                //Currently just grabs all accounts
               List<Account> allAccounts = App.accountServices.accountsOwnedByUser();
               for (int i = 0; i < allAccounts.size(); i++){
                   System.out.println(allAccounts.get(i));
               }
               break;
            }
            default:{
                System.out.println("Invalid option, please try again");
                //If something is mistyped, relaunch option with saved "user credentials", this is awful
                loggedIn(currentUser);

            }
        }


    }



}
