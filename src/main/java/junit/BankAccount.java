package junit;

public class BankAccount {
    private boolean isBlocked = false;
    private Integer amount;
    private String currency;

    private final String firstName;
    private final String secondName;

    public BankAccount(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }



    public void withdraw(int amount) {
        if (this.amount == null) {
            throw new IllegalStateException("Можно снять средства только после активации счета");
        }
        if (this.amount - amount < 0) {
            throw new IllegalStateException("Нельзя снять больше чем есть на счету");
        }
        if (this.isBlocked) {
            throw new IllegalStateException("Нельзя снять средства, если счет заблокирован");
        }
        this.amount -= amount;
    }

    public void transfer(BankAccount otherAccount, int amount) {
        if (this.amount == null || otherAccount == null) {
            throw new IllegalStateException("Один из счетов не активирован");
        }
        if (this.isBlocked || otherAccount.isBlocked) {
            throw new IllegalStateException("Один из счетов заблокирован");
        }
        if (this.amount - amount < 0) {
            throw new IllegalStateException("Нельзя снять больше чем есть на счету");
        }
        this.amount = this.amount - amount;
        otherAccount.amount += amount;
    }

    public void deposit(int amount) {
        this.amount += amount;
    }

    public void block() {
        this.isBlocked = true;
    }

    public void activate(String currency) {
        this.amount = 0;
        this.currency = currency;
    }

    public Integer getAmount() {
        if (amount == null) {
            throw new IllegalStateException("Счёт не активирован.");
        }
        return this.amount;
    }

    public String getCurrency() {
        return currency;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public String[] getFullName() {
        return new String[]{firstName, secondName};
    }
}
