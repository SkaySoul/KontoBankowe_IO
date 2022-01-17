package testing;
import application.Account;
import application.DataSet;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class TestData {
    public DataSet dataSetTest;
    public TestData(int qtt){
        dataSetTest = new DataSet();
        dataSetTest.setAccountList(createTestAccounts(qtt));
    }

    public List<Account> createTestAccounts(int qtt){
        List<Account> list= new ArrayList<Account>();
        for(int i = 0; i< qtt; i++){
            Account account = new Account("login" + i, "password" + i, 11111111L * i, 1000, "Name"+i, "Surname1"+i, 0);
            list.add(account);
        }
        return list;
    }
}
