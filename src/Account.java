import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.*;
@JsonDeserialize
public class Account implements Config {


    private String login;

    private String password;

    private long accountNumber;

    public float currentBalance;

    private String ownerName;

    private String ownerSurname;
    public List<String> operationList;

    public float creditBalance;
    public boolean creditStatus;


    public Account( @JsonProperty("login") String login ,@JsonProperty("password") String password, @JsonProperty("accountNumber") long accountNumber,@JsonProperty("currentBalance")
            float currentBalance,@JsonProperty("ownerName") String ownerName,@JsonProperty("ownerSurname") String ownerSurname,@JsonProperty("creditBalance") float creditBalance) {

        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.operationList = new ArrayList<>();
        this.creditBalance = creditBalance;
        this.creditStatus = creditBalance <= maxCreditValue;
        ;
        this.login = login;
        this.password = password;

    }

    public void makeOperation(Account account,String operationType, float value, DataSet dataSet, Scanner in){
        switch (operationType.toLowerCase(Locale.ENGLISH)) {
            case "paymentadd" : {
                Payment operation = new Payment();
                operation.changeBalance(account, value);
                operation.writeToHistory(account, value);
                break;
            }
            case "paymentsub" : {
                Payment operation = new Payment();
                if (operation.checkConditions(account, value)) {
                    value = -value;
                    operation.changeBalance(account, value);
                    operation.writeToHistory(account, value);
                } else printMessage("Conditions for " + operationType + " is not met");
                break;
            }
            case "transfer" : {
                printMessage("Please insert receiver account number");
                int resnum = in.nextInt();
                Transfer operation = new Transfer();
                if (operation.checkConditions(account, value)) {
                    if (operation.searchReceiver(resnum, dataSet.getAccountList()) != null) {
                        Account receiverAccount = operation.searchReceiver(resnum, dataSet.accountList);
                        operation.changeBalance(account, value);
                        operation.changeBalance(receiverAccount, value);
                        operation.writeToHistory(account, value);
                    } else printMessage("Undefined account number");

                } else printMessage("Conditions for " + operationType + " is not met");
                printMessage("Transfer was done");
                break;
            }
            case "crediting" : {
                Crediting operation = new Crediting();
                if (operation.checkConditions(account, value)) {
                    operation.changeBalance(account, value);
                    operation.getCredit(account, value);
                    operation.writeToHistory(account, value);
                } else printMessage("Conditions for " + operationType + " is not met");
            }
            default :{
                break;
            }
        }
    }

    public void getAccountStatus(){
        printMessage(getLogin());
        printMessage(getOwnerName() + " " + getOwnerSurname());
        printMessage(Long.toString(getAccountNumber()));
        printMessage(Float.toString(getCurrentBalance()));
    }

    public void showOperationList(){
        for (String operations: getOperationList()) {
            printMessage(operations);
        }
    }



    public float getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(float creditBalance) {
        this.creditBalance = creditBalance;
    }

    public boolean isCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(boolean creditStatus) {
        this.creditStatus = creditStatus;
    }

    public List<String> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<String> operationList) {
        this.operationList = operationList;
    }

    public void addToOperationList(String operation) {
        this.operationList.add(operation);
    }



    public float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}

