package testing;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class RunnerExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Boolean testResult = context.getExecutionException().isPresent();
        if(!testResult){
            System.out.println("Test Passed");
        }
        else System.out.println("Test Failed");
    }
}
