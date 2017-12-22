import java.io.IOException;
import java.util.Scanner;

public class TerminalUI {
    private TerminalImpl terminal = new TerminalImpl(new TerminalServer(), new PinValidator());

    private Scanner in = new Scanner(System.in);

    public void start() {
        while (true) {
            String cardNumber = cardMenu();
            if (!cardNumber.isEmpty()) {
                operationMenu(cardNumber);
                break;
            }
        }
        System.out.println("Друг, забери карту пожалуйста!");
    }

    private String cardMenu() {
        System.out.println("Уважаемый пользователь, вставьте карту пожалуйста!");
        String cardNumber = in.nextLine().replaceAll("\\s+", "");

        if (cardNumber.equalsIgnoreCase("q")) return "";

        while (true) {
            System.out.println("Введите ПИН-код.");
            String pin = in.nextLine().replaceAll("\\s", "");

            if (pin.equalsIgnoreCase("q")) return "";

            try {
                if (!terminal.checkPin(cardNumber, pin)) {
                    System.out.println("Неверный ПИН-код карты!");
                    continue;
                }
            } catch (AccountIsLockedException e) {
                System.out.println("Аккаунт заблокирован. До разблокировки осталось секунд: " + e.getMessage());
                continue;
            }
            return cardNumber;

        }
    }

    private void operatinMenu(String cardNumber) {
        while (true) {
            System.out.println("\nВыбор операции:\n" +
                    "1. Проверить баланс карты\n" +
                    "2. Снять наличные\n" +
                    "3. Внести наличные\n" +
                    "4. Завершить работу\n" +
                    "Введите номер требуемой операции [1-4]: ");
            int operation = in.nextInt();

            try {
                if (operation == 1) getBalance(cardNumber);
                if (operation == 2) getMoney(cardNumber);
                if (operation == 3) putMoney(cardNumber);
                if (operation == 4) return;
            } catch (SecurityException e) {
                System.out.println("Введите ПИН-КОД!");
            } catch (AccountIsLockedException e) {
                System.out.println("Аккаунт заблокирован. До разблокировки осталось секунд: " + e.getMessage());
            } catch (InsufficientFundsExceptions e) {
                System.out.println("Недостаточно средств на счете. Операция не выполнена.");
            } catch (IllegalArgumentException e) {
                System.out.println("Сумма должна быть кратна 100. Операция не выполнена.");
            } catch (IOException e) {
                System.out.println("Возникли проблемы со связью, попробуйте повторить операцию.");
            }
        }
    }

    private void getBalance(String cardNumber) throws IOException {
        System.out.println("\n Баланс карты: " + terminal.checkBalance(cardNumber));

    }
    private void getMoney(String carNumber) throws IOException{
        System.out.println("\n Снятие наличных");
        System.out.println("Введите сумму кратную 100: ");
        int amount = in.nextInt();
        terminal.getMoney(cardNumber, amount);
        System.out.println("Операция выполнена успешно, заберите деньги!");
    }
    private void putMoney(String carNumber) throws IOException{
        System.out.println("\n Внесение наличных.");
        System.out.println("Введите сумму кратную 100: ");
        int amount = in.nextInt();
        terminal.putMoney(carNumber, amount);
        System.out.println("Деньги зачислены!");
    }
}

