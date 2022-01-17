package application;

import application.Account;
import application.Config;

public class Crediting extends Operation implements Config {



    public boolean checkValue(Account account, float value){
        return !(account.getCreditBalance() + value > maxCreditValue);
    }

    public void getCredit(Account account, float value){
            account.setCreditBalance(account.getCreditBalance()+value);
            if(account.getCreditBalance()+value > maxCreditValue){
                account.setCreditStatus(false);
            }
    }



    public void writeToHistory(Account account,float value){
        account.addToOperationList("Crediting: " +  value);
    }

    public boolean checkConditions(Account account, float value){
        if (account.isCreditStatus()){
            return checkValue(account, value);
        }
        else return false;
    }
}
