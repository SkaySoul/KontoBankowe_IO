package testingJMockit;
import mockit.*;
import org.junit.*;
import application.*;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;


@TestInstance(PER_CLASS)
public class AccountTestJMockit {

    @Test
    public void makeOperationPaymentAdd(@Mocked Account testAccount) {

        float value = 10;
        float balanceBefore = 100;
        float balanceAfter = balanceBefore + value;
        new Expectations() {
            {
                testAccount.getCurrentBalance();
                result = balanceAfter;
            }
        };
        Payment operation = new Payment();
        operation.changeBalance(testAccount, value);
        operation.writeToHistory(testAccount, value);
        new VerificationsInOrder() {
            {
                testAccount.getCurrentBalance();
                maxTimes = 1;
            }
        };
    }

    @Test
    public void makeOperationPaymentSub(@Mocked Account testAccount) {

        float value = 10;
        float balanceBefore = 100;
        float balanceAfter = balanceBefore - value;
        new Expectations() {
            {
                testAccount.getCurrentBalance();
                result = balanceAfter;
            }
        };
        Payment operation = new Payment();
        value = -value;
        operation.changeBalance(testAccount, value);
        operation.writeToHistory(testAccount, value);
        new VerificationsInOrder() {
            {
                testAccount.getCurrentBalance();
                maxTimes = 1;
            }
        };
    }

    @Test
    public void makeOperationPaymentCredit(@Mocked Account testAccount) {

        float value = 10;
        float CreditBefore = 0;
        float CreditAfter = CreditBefore - value;
        new Expectations() {
            {
                testAccount.getCreditBalance();
                result = CreditAfter;
            }
        };
        Crediting operation = new Crediting();
        operation.changeBalance(testAccount, value);
        operation.getCredit(testAccount, value);
        operation.writeToHistory(testAccount, value);
        new VerificationsInOrder() {
            {
                testAccount.getCreditBalance();
                maxTimes = 1;
            }
        };
    }

    @Test
    public void makeOperationPaymentTransfer(@Mocked Account testAccount1, @Mocked Account testAccount2) {

        float value = 10;
        float beforeTransfer1 = 50;
        float beforeTransfer2 = 0;
        float afterTransfer1 = beforeTransfer1 - value;
        float afterTransfer2 = beforeTransfer2 + value;
        new Expectations() {
            {
                testAccount1.getCurrentBalance();
                result = afterTransfer1;
                testAccount2.getCurrentBalance();
                result = afterTransfer2;
            }
        };
        Crediting operation = new Crediting();
        operation.changeBalance(testAccount1, -value);
        operation.changeBalance(testAccount2, value);
        operation.writeToHistory(testAccount1, value);
        new VerificationsInOrder() {
            {
                testAccount1.getCurrentBalance();
                maxTimes = 1;
                testAccount2.getCurrentBalance();
                maxTimes = 1;
            }
        };
    }
}