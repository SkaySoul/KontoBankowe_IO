package testing;
import application.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(RunnerExtension.class)
class AccountTest {
    TestData testData;


    @BeforeEach
    public void createTestData(){
        testData = new TestData(10);
        System.out.println("Account Test Started");
    }


    public Stream<Float> createValues(){
    return Stream.of(100F, 200F, 300F, 400F, 500F);
    }



    @ParameterizedTest
    @MethodSource("createValues")
    void makeOperationPaymentAdd(float value) {
        ArrayList<Float> expected = new ArrayList<Float>();
        ArrayList<Account> testAccounts = new ArrayList<>(testData.dataSetTest.getAccountList());
        for (int i = 0; i< 10; i++){
            testAccounts.get(i).makeOperation(testAccounts.get(i), "paymentadd", value, testData.dataSetTest, 0);
            expected.add(testAccounts.get(i).getCurrentBalance());
        }

        ArrayList <Float> actual = new ArrayList<Float>();
        for(int i = 0; i<10; i++){
            actual.add(1000F+value);
        }
        assertEquals(expected,actual);
    }



    @ParameterizedTest
    @MethodSource("createValues")
    void makeOperationPaymentSub(float value) {

        ArrayList<Float> expected = new ArrayList<Float>();
        ArrayList<Account> testAccounts = new ArrayList<>(testData.dataSetTest.getAccountList());
        for (int i = 0; i< 10; i++){
            testAccounts.get(i).makeOperation(testAccounts.get(i), "paymentsub", value, testData.dataSetTest, 0);
            expected.add(testAccounts.get(i).getCurrentBalance());
        }

        ArrayList <Float> actual = new ArrayList<Float>();
        for(int i = 0; i<10; i++){
            actual.add(1000F-value);
        }
        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @MethodSource("createValues")
    void makeOperationPaymentCredit(float value) {

        ArrayList<Float> expectedBalance = new ArrayList<Float>();
        ArrayList<Float> expectedCredit = new ArrayList<Float>();
        ArrayList<Account> testAccounts = new ArrayList<>(testData.dataSetTest.getAccountList());
        for (int i = 0; i< 10; i++){
            testAccounts.get(i).makeOperation(testAccounts.get(i), "crediting", value, testData.dataSetTest, 0);
            expectedBalance.add(testAccounts.get(i).getCurrentBalance());
            expectedCredit.add(testAccounts.get(i).getCreditBalance());
        }

        ArrayList <Float> actualBalance= new ArrayList<Float>();
        ArrayList <Float> actualCredit= new ArrayList<Float>();
        for(int i = 0; i<10; i++){
            actualBalance.add(1000F + value);
            actualCredit.add(value);
        }
        assertEquals(expectedBalance,actualBalance);
        assertEquals(expectedCredit, actualCredit);

    }

    @ParameterizedTest
    @MethodSource("createValues")
    void makeOperationPaymentTransfer(float value) {

        ArrayList<Float> expectedBalanceReceiver = new ArrayList<Float>();
        ArrayList<Float> expectedBalanceSender = new ArrayList<Float>();
        ArrayList<Account> testAccounts = new ArrayList<>(testData.dataSetTest.getAccountList());
        for (int i = 0; i< 9; i++){
            testAccounts.get(i).makeOperation(testAccounts.get(i), "transfer", value, testData.dataSetTest, 99999999);
            expectedBalanceReceiver.add(testAccounts.get(9).getCurrentBalance());
            expectedBalanceSender.add(testAccounts.get(i).getCurrentBalance());
        }

        ArrayList <Float> actualBalanceReceiver= new ArrayList<Float>();
        ArrayList <Float> actualBalanceSender= new ArrayList<Float>();
        for(int i = 0; i<9; i++){
                actualBalanceReceiver.add(1000F + value*(i+1));
                actualBalanceSender.add(1000F-value);


        }
        assertEquals(expectedBalanceReceiver, actualBalanceReceiver);
        assertEquals(expectedBalanceSender, actualBalanceSender);
    }


    @AfterAll
    static void testEnd(){
        System.out.println("All Account tests are completed");
    }


}

