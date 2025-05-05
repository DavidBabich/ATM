package atm;

public class ATMProgramWithPostgres {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        ATM atm = new ATM(dbManager);

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Введите PIN: ");
        String pin = scanner.nextLine();

        if (atm.login(pin)) {
            atm.displayBalance();
            atm.displayTransactions();
        } else {
            System.out.println("Неверный PIN. Доступ запрещён.");
        }

        scanner.close();
    }
}