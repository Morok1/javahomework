public class AccountIsLockedException extends IllegalStateException {
    public AccountIsLockedException(int sec){
        super(String.valueOf(sec));
    }
}
