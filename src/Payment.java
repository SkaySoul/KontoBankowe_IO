public class Payment extends Operation{


    public void writeToHistory(Account account, int value){
        account.getOperationList().add("transfer: " +  value);

    }


    public boolean checkConditions(Account account, int value){

        return true;
    }

}
