import java.util.ArrayList;
import java.util.List;

public abstract class Operation {
    private List<String> operationType;

    public void changeBalance(Account account,boolean type, float value){

    }

    public float checkBalance(Account account){
        return account.getCurrentBalance();
    }

    public abstract void writeToHistory(Account account, int value);
    public abstract boolean checkConditions(Account account, int value);

}
