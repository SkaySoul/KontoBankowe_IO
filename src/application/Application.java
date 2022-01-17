package application;

import application.Account;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Application {



    public static void printMessage(String message){
        System.out.println(message);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataSet dataSet = new DataSet();
        Account currentAccount = null;
        Scanner in = new Scanner(System.in);
        while (true){
            String login, password;
            boolean found = false;
            printMessage("Please insert login: ");
            login = in.nextLine();
            for(int i = 0; i< dataSet.getAccountList().size(); i++){
                if(Objects.equals(dataSet.getAccountList().get(i).getLogin(), login)){
                    found = true;
                    printMessage("Please insert password: ");
                    password = in.nextLine();
                    if (Objects.equals(dataSet.getAccountList().get(i).getPassword(), password)){
                        currentAccount = dataSet.accountList.get(i);
                        startApplication(currentAccount, dataSet, in);
                    }
                    else printMessage("Incorrect password, please try again...");
                }

            }
            if(!found){
                printMessage("Cannot find account by inserted login, please try again...");
            }

            while (true){
                printMessage("Do you want to exit application? (y/n)");
                String question = in.nextLine();
                if(Objects.equals(question, "y") || Objects.equals(question, "n")){
                    if (question.equals("y")){
                        in.close();
                        dataSet.writeAccounts();
                        printMessage("Goodbye =)");
                        return;
                    }
                    if(question.equals("n")){
                        break;
                    }
                }
                else printMessage("Incorrect answer, please try again...");
            }


        }

    }

    public static void startApplication(Account account, DataSet dataset, Scanner in) {
        while(true){
            printMessage("Please insert number of wanted operation: ");
            printMessage("1. application.Transfer");
            printMessage("2. application.Payment");
            printMessage("3. application.Crediting");
            printMessage("4. Show information about account");
            printMessage("5. Show operation list");
            printMessage("6. Logout");
            int number = in.nextInt();
            switch (number) {
                case 1: {
                    printMessage("Please insert value of transfer: ");
                    float value = in.nextFloat();
                    printMessage("Please insert receiver account number");
                    int resnum = in.nextInt();
                    account.makeOperation(account,"transfer", value, dataset, resnum);

                    break;
                }
                case 2: {
                    printMessage("Please insert value of payment: ");
                    float value = in.nextFloat();
                    String type;
                    while (true) {
                        printMessage("You want payin (insert <add>) or payout (insert <sub>) money?");
                        type = in.nextLine();
                        if (Objects.equals(type, "add") || Objects.equals(type, "sub")) {
                            break;
                        } else printMessage("Unresolved type of operation, please try again...");
                    }
                    account.makeOperation(account,"payment" + type, value, dataset, 0);

                    break;
                }
                case 3: {
                    printMessage("Please insert value of crediting: ");
                    float value = in.nextFloat();
                    account.makeOperation(account,"crediting", value, dataset, 0);

                    break;
                }
                case 4: {
                    account.getAccountStatus();
                    break;
                }
                case 5: {
                    account.showOperationList();
                    break;
                }
                case 6: {
                    return;
                }
                default: {
                    printMessage("Unresolved type of operation, please try again...");
                    break;
                }
            }
        }
    }
}
