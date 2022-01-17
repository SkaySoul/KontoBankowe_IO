package application;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;

public class DataSet{

    public List<Account> accountList;
    DatabaseConnector dbConnector;

    public DataSet() {

        this.accountList = new ArrayList<>();
        getAccounts();
        dbConnector = new DatabaseConnector();
        try {
            dbConnector.getDbConnection();
            //Application.printMessage("Database connection was done...");
        }
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void addToDataSet(Account account){
        accountList.add(account);
    }

    public void getAccounts() {
        try{
            File file = new File("src/application/Accounts.json");
            ObjectMapper mapper = new ObjectMapper();
            Account[] accounts = mapper.readValue(file, Account[].class);
            setAccountList(List.of(accounts));
        }
        catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }


    }

    public void writeAccounts(){
        try{
            File file = new File("src/application/Accounts.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, accountList);

        }
        catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }

    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }



}
