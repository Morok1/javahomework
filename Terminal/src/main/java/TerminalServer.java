import java.io.IOException;
import java.util.Map;
import java.util.Random;

public class TerminalServer implements Terminal {
//    База данных карт: номер карты - баланс

    private Map<String, Long> cardDB= new HashMap<>();
    public TerminalServer(){
        cardDB.put("1", 1L);
        cardDB.put("2", 2L);
        cardDB.put("3", 3L);
        cardDB.put("3", 4L);
        cardDB.put("5", 5L);
    }


    @Override
    public long checkBalance(String cardNumber) throws IOException {
            ConnectionErrorSimulator();
            return cardDB.get(cardNumber);
        return 0;
    }
    @Override
    public void getMoney(String cardNumber, long amount) throws IOException {
        ConnectionErrorSimulator();
        long balance = cardDB.get(cardNumber);
        long newBalance = balance - amount;
        if(newBalance < 0) throw new InsufficientFundsExceptions("Недостаточно средств!");
        cardDB.put(cardNumber, newBalance);

    }

    public void putMoney(String cardNumber, long amount) throws IOException {
        ConnectionErrorSimulator();
        long balance = cardDB.get(cardNumber);
        long newBalance = balance + amount;
        cardDB.put(cardNumber, newBalance);
    }
    /*Метод симулирует проблемы с сетью!*/
    private void ConnectionErrorSimulator() throws IOException{
        final Random rnd = new Random();
        if(rnd.nextInt(3) == 2) throw new IOException("Проблемы с сетью!");
    }
}
