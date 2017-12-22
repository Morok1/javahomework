import java.io.IOException;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private PinValidator pinValidator;
    private LockHelper lockHelper = new LockHelper();

    private int pinErrorCount = 0;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    public boolean checkPin(String cardNumber, String pin){
        if(lockerHelper.isLocked()){
            throw new AccountIsLockedException((int) (Math.ceil(lockHelper.getCoutdown() / 1000.0)));
        }
        if(pinValidator.validate(cardNumber, pin)){
            pinErrorCount = 0;
            return true;
        }
        pinErrorCount++;
        if(pinErrorCount == 3){
            lockHelper.lock();
            pinErrorCount = 0;
        }

        return false;
    }
    @Override
    public void getMoney(String cardNumber, long amount) throws IOException {
        if(!pinValidator.isAuthorized)
            throw new SecurityException("Нет авторизации, введите ПИН-код");
        if(lockerHelper.isLocked())
            throw new AccountIsLockedException((int) (Math.ceil(lockerHelper.getCountdown() / 1000.0)));

        if(amount % 100 != 0 )
            throw new IllegalArgumentException("Сумма не кратна 100");
        server.getMoney(cardNumber, amount);
    }
    @Override
    public void putMoney(String cardNumber, long amount) throws IOException {
        if(!pinValidator.isAuthorized)
            throw new SecurityException("Нет авторизации, введите ПИН-код");

            if(lockerHelper.isLocked())
                throw new AccountIsLockedException((int) (Math.ceil(lockerHelper.getCountdown() / 1000.0)));

            if(amount % 100 != 0 )
                throw new IllegalArgumentException("Сумма не кратна 100");
            server.putMoney(cardNumber, amount);

    }


    public long checkBalance(String cardNumber) throws IOException {
        if(!pinValidator.isAuthorized)
            throw new SecurityException("Нет авторизации, введите ПИН-код");
        if(lockerHelper.isLocked())
            throw new AccountIsLockedException((int) (Math.ceil(lockerHelper.getCountdown() / 1000.0)));

        if(amount % 100 != 0 )
            throw new IllegalArgumentException("Сумма не кратна 100");
        return server.checkBalance(cardNumber);
    }
    /*Внутренний класс, отслеживающий время блокировки аккаунта*/
    private static class LockHelper{
        private static final long lockInterval = 5000;
        long lockTime = -1;

        boolean isLocked(){
            if(lockTime == -1) return false; //Отсутсвует блокировка
            if(lockTime + lockInterval > System.currentTimeMillis()){
                return true;
            }
//            Истёк интервал блокировки
            lockTime = -1;
            return false;
        }
//        возвращает время в милисекундах до разблокировки.
        long getCoutDown(){
            if(lockTime != -1){
                return lockTime + lockInterval - System.currentTimeMillis();
                return 0;
            }
        }
    }
}
