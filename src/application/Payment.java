package application;

import application.Account;
import application.Operation;

public class Payment extends Operation {


    public void writeToHistory(Account account, float value){
        account.addToOperationList("application.Payment: " +  value);

    }


    public boolean checkConditions(Account account, float value){
        return !(checkBalance(account) - value < 0);
    }

}
