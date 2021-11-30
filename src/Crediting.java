public class Crediting extends Operation{



    public boolean checkValue(Account account, float value){
        return !(account.getCreditBalance() + value > DataSet.maxCreditValue);
    }

    public void getCredit(Account account, float value){
            account.setCreditBalance(account.getCreditBalance()+value);
            if(account.getCreditBalance()+value > DataSet.maxCreditValue){
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
