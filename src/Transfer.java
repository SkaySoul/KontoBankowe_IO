public class Transfer extends Operation{

    public boolean searchReceiver(long receiverAccNum){

        return true;
    }


    public boolean checkConditions(Account account){

        return true;
    }

    @Override
    public void writeToHistory(Account account){

    }

}
