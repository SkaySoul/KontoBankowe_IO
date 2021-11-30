public class Payment extends Operation{


    public void writeToHistory(Account account, float value){
        account.addToOperationList("Payment: " +  value);

    }


    public boolean checkConditions(Account account, float value){
        return !(checkBalance(account) - value < 0);
    }

}
