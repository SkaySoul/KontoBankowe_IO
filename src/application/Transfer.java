package application;

import application.Account;
import application.Operation;

import java.util.List;

public class Transfer extends Operation {


    public Account searchReceiver(long receiverAccNum, List<Account> accountList) {
        for (Account account : accountList) {
            if (account.getAccountNumber() == receiverAccNum) {
                return account;
            }
        }
        return null;
    }


    public boolean checkConditions(Account account, float value) {
        return checkBalance(account) >= value;
    }


    public void writeToHistory(Account account, float value) {
        account.addToOperationList("Transfer: " + value);
    }

}
