package testingJMockit;
import mockit.*;
import org.junit.*;
import application.*;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(PER_CLASS)
public class TransferTestJMockit {

    @Test(expected=NullPointerException.class)
    public void SearchReceiverNull(@Mocked Account testAccount) {

        ArrayList<Account> accountListTest = new ArrayList<Account>(5);
        Transfer transfer = new Transfer();
        new Expectations() {
            {
                transfer.searchReceiver(testAccount.getAccountNumber(),accountListTest);
                result = null;
            }
        };
        assertEquals(transfer.searchReceiver(testAccount.getAccountNumber(),accountListTest), testAccount);
    };

    @Test
    public void SearchReceiverTrue(@Mocked Account testAccount) {

        ArrayList<Account> accountListTest = new ArrayList<Account>(1);
        accountListTest.add(testAccount);
        Transfer transfer = new Transfer();
        new Expectations() {
            {
                transfer.searchReceiver(testAccount.getAccountNumber(),accountListTest);
                result = testAccount.getAccountNumber();
            }
        };
        assertEquals(transfer.searchReceiver(testAccount.getAccountNumber(),accountListTest), testAccount);
        new VerificationsInOrder() {
            {
                testAccount.equals(any);
                maxTimes = 1;
            }
        };
    };
}

