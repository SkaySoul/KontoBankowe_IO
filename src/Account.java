import java.util.*;

public class Account {

    private long accountNumber;
    private float currentBalance;
    private String ownerName;
    private String ownerSurname;
    private List<String> operationList;
    private float creditBalance;
    private boolean creditStatus;


    public Account(long accountNumber, float currentBalance, String ownerName, String ownerSurname, float creditBalance) {
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.operationList = new ArrayList<>();
        this.creditBalance = creditBalance;
        this.creditStatus = creditBalance <= DataSet.maxCreditValue;

    }

    public void makeOperation(String operationType, float value, DataSet dataSet){
        switch(operationType.toLowerCase(Locale.ENGLISH)){
            case"paymentadd":{
                Payment operation = new Payment();
                    operation.changeBalance(this,  value);
                    operation.writeToHistory(this, value);
            }

            case"paymentsub":{
                Payment operation = new Payment();
                if(operation.checkConditions(this, value)){
                    value = -value;
                    operation.changeBalance(this,  value);
                    operation.writeToHistory(this, value);
                }
                else printMessage("Conditions for " + operationType + " is not met");
            }

            case"transfer":{
                Scanner in = new Scanner(System.in);
                printMessage("Please insert receiver account number");
                int resnum = in.nextInt();
                Transfer operation = new Transfer();
                if(operation.checkConditions(this, value)){
                    if (operation.searchReceiver(resnum, dataSet.getAccountList() ) != null) {
                        Account receiverAccount = operation.searchReceiver(resnum, dataSet.getAccountList());
                        operation.changeBalance(this,  value);
                        operation.changeBalance(receiverAccount, value);
                        operation.writeToHistory(this, value);
                    }
                    else printMessage("Undefined account number");

                }
                else printMessage("Conditions for " + operationType + " is not met");
            }
            case"crediting":{
                Crediting operation = new Crediting();
                if (operation.checkConditions(this, value)){
                    operation.changeBalance(this, value);
                    operation.getCredit(this, value);
                    operation.writeToHistory(this, value);
                }
                else printMessage("Conditions for " + operationType + " is not met");
            }
        }
    }



    public float getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(float creditBalance) {
        this.creditBalance = creditBalance;
    }

    public boolean isCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(boolean creditStatus) {
        this.creditStatus = creditStatus;
    }

    public List<String> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<String> operationList) {
        this.operationList = operationList;
    }

    public void addToOperationList(String operation) {
        this.operationList.add(operation);
    }



    public float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}

