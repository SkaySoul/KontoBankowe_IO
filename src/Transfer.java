import java.util.List;

public class Transfer extends Operation{




    public Account searchReceiver(int receiverAccNum, List<Account> accountList){
        for (Account account : accountList) {
            if (account.getAccountNumber() == receiverAccNum) {
                return account;
            }
        }
        return null;
    }


    public boolean checkConditions(Account account, int value){
        return checkBalance(account) >= value;
    }


    public void writeToHistory(Account account, int value){
        account.getOperationList().add("transfer: " +  value);
    }

}
