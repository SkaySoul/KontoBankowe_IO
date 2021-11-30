import java.util.ArrayList;
import java.util.List;

public class DataSet {

    private List<Account> accountList;
    public static final float maxCreditValue = 10000;


    public DataSet(){
        this.accountList = new ArrayList<Account>();
    }

    public void addToDataSet(Account account){
        accountList.add(account);
    }





    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }





}
