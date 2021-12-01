import java.util.Objects;
import java.util.Scanner;

public class Application {



    public static void printMessage(String message){
        System.out.println(message);
    }

    public static void main(String[] args){
        DataSet dataSet = new DataSet();
        Account currentAccount = null;
        Scanner in = new Scanner(System.in);
        while (true){
            String login, password;

            printMessage("Please insert login: ");
            login = in.nextLine();
            for(int i = 0; i< dataSet.getAccountList().size(); i++){
                if(Objects.equals(dataSet.getAccountList().get(i).getLogin(), login)){
                    printMessage("Please insert password: ");
                    password = in.nextLine();
                    if (Objects.equals(dataSet.getAccountList().get(i).getPassword(), password)){
                        currentAccount = dataSet.getAccountList().get(i);
                        startApplication(currentAccount, dataSet);
                        return;
                    }
                }
            }
            printMessage("Cannot find account by inserted login, please try again...");
        }

    }

    public static void startApplication(Account account, DataSet dataset) {
        while(true){
            Scanner in = new Scanner(System.in);
            printMessage("Please insert number of wanted operation: ");
            printMessage("1: Transfer");
            printMessage("2. Payment");
            printMessage("3. Crediting");
            printMessage("4. Exit");
            int number = in.nextInt();
            switch (number) {
                case 1 -> {
                    printMessage("Please insert value of transfer: ");
                    float value = in.nextFloat();
                    account.makeOperation("transfer", value, dataset);
                    in.close();
                }
                case 2 -> {
                    printMessage("Please insert value of payment: ");
                    float value = in.nextFloat();
                    String type;
                    while (true) {
                        printMessage("You want payin (insert <add>) or payout (insert <sub>) money?");
                        type = in.nextLine();
                        if (Objects.equals(type, "add") || Objects.equals(type, "sub")) {
                            break;
                        }
                        else printMessage("Unresolved type of operation, please try again...");
                    }
                    account.makeOperation("payment" + type, value, dataset);
                    in.close();
                }
                case 3 -> {
                    printMessage("Please insert value of crediting: ");
                    float value = in.nextFloat();
                    account.makeOperation("crediting", value, dataset);
                    in.close();
                }
                case 4-> {
                    return;
                }
                default -> {
                    printMessage("Unresolved type of operation, please try again...");
                    in.close();
                }
            }
        }
    }
}
