import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataSet {

    private List<Account> accountList;
    public static final float maxCreditValue = 10000;


    public DataSet(){

        this.accountList = new ArrayList<Account>();
        getAccounts();
    }

    public void addToDataSet(Account account){
        accountList.add(account);
    }

    public void getAccounts() {
        try {
            File file = new File("C:\\Accounts.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            int accountAmount = Integer.parseInt(line);
            for(int i = 0; i<accountAmount; i++){
                   String login = reader.readLine();
                   String password = reader.readLine();
                   long  accountNumber = Long.parseLong(reader.readLine());
                   float currentBalance = Float.parseFloat(reader.readLine());
                   String ownerName = reader.readLine();
                   String ownerSurname= reader.readLine();
                   float creditBalance = Float.parseFloat(reader.readLine());
                   Account account = new Account(login,password,accountNumber,currentBalance, ownerName, ownerSurname, creditBalance);
                   addToDataSet(account);
                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }





}
