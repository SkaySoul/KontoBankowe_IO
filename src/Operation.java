import java.util.ArrayList;
import java.util.List;

public abstract class Operation {
    private List<String> operationType;

    public void changeBalance(Account account, float value){
        account.setCurrentBalance(account.getCurrentBalance()+value);
    }

    public float checkBalance(Account account){
        return account.getCurrentBalance();
    }

    public abstract void writeToHistory(Account account, float value);
    public abstract boolean checkConditions(Account account, float value);

}
