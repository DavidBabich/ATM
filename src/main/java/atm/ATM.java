package atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
class ATM {
    private AccountHolder accountHolder;
    private DatabaseManager dbManager;

    @Autowired
    public ATM(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean login(String pin) {
        accountHolder = dbManager.getAccountHolderByPin(pin);
        return accountHolder != null;
    }

    public void displayBalance() {
        if (accountHolder != null) {
            System.out.printf("Баланс для %s: %.2f%n", accountHolder.getName(), accountHolder.getBalance());
        }
    }

    public void displayTransactions() {
        if (accountHolder != null) {
            List<Transaction> transactions = dbManager.getTransactions(accountHolder.getId());
            if (transactions.isEmpty()) {
                System.out.println("Недавних транзакций нет.");
            } else {
                System.out.println("Недавние транзакции:");
                for (Transaction t : transactions) {
                    System.out.println(t);
                }
            }
        }
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }
}

