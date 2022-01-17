package testing;

import application.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;



class DataSetTest {
    TestData testData;


    @BeforeEach
    public void createTestData(){
        testData = new TestData();
        System.out.println("Dataset Test Started");
    }

    @Test
    void getAccounts() throws IOException {
        ArrayList<Account> actualAccounts = new ArrayList<>();


        File file = new File("src/testing/AccountsExpected.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, testData.dataSetTest.accountList);

        ArrayList<Account> expectedAccounts = new ArrayList<Account>(testData.dataSetTest.getAccountList());



        actualAccounts.add(new Account("test1","test1",11111111,100, "", "", 100));


    }

    @Test
    void writeAccounts() {

    }
}