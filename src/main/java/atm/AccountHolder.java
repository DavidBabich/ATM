package atm;

class AccountHolder {
    private int id;
    private String name;
    private String pin;
    private double balance;

    public AccountHolder(int id, String name, String pin, double balance) {
        this.id = id;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean authenticate(String inputPin) {
        return pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}