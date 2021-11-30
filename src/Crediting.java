public class Crediting extends Operation{

    public boolean checkConditions(Account account){

        return true;
    }

    public boolean checkValue(Account account, int value){

        return true;
    }

    public void getCredit(Account account, int value){

    }


    @Override
    public void writeToHistory(Account account){

    }
}
