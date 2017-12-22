import java.util.HashMap;
import java.util.Map;

public class PinValidator {
    private Map<String, String> pinDB = new HashMap<String, String>();
    boolean isAuthorized = false;

    public PinValidator(){
        pinDB.put("1","1");
        pinDB.put("2","2");
        pinDB.put("3","3");
    }

    boolean validate (String cardNumber, String pin){
        if(pin.length() != 4) return false;
        isAuthorized = pinDB.containsKey(cardNumber) && pinDB.get(cardNumber).equals(pin);
        return isAuthorized;
    }
}
