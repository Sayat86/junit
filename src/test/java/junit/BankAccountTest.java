package junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    @Test
    void shouldNotBeBlockedWhenCreated() {
        BankAccount account = new BankAccount("a", "b");
        assertFalse(account.isBlocked());
    }

    @Test
    void shouldReturnZeroAmountAfterActivation() {
        BankAccount account = new BankAccount("a", "b");
        account.activate("KZT");
        assertEquals(0, account.getAmount());
        assertEquals("KZT", account.getCurrency());
    }

    @Test
    void t() {
        BankAccount account = new BankAccount("a", "b");
        String[] fullName = account.getFullName();
        String[] expectedName = {"a", "b"};
        assertArrayEquals(expectedName, fullName);
    }
    @Test
    void withdrawTest() {
        BankAccount account = new BankAccount("a", "b");
        account.activate("KZT");
        account.deposit(5000);
        int expected = 3000;

        account.withdraw(2000);
        assertEquals(expected, account.getAmount());
    }

    @Test
    void withdrawTestNotActivated() {
        BankAccount account = new BankAccount("a", "b");
        String expected = "Можно снять средства только после активации счета";

        Executable executable = () -> account.withdraw(5000);

        IllegalStateException ex = assertThrows(IllegalStateException.class, executable);
        assertEquals(expected, ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenInsufficientBalance() {
        BankAccount account = new BankAccount("a", "b");
        account.activate("KZT");
        account.deposit(5000);
        String expected = "Нельзя снять больше чем есть на счету";

        Executable executable = () -> account.withdraw(6000);

        IllegalStateException ex = assertThrows(IllegalStateException.class, executable);
        assertEquals(expected, ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBlocked() {
        BankAccount account = new BankAccount("a", "b");
        account.activate("KZT");
        account.deposit(5000);
        account.block();
        String expected = "Нельзя снять средства, если счет заблокирован";

        Executable executable = () -> account.withdraw(3000);

        IllegalStateException ex = assertThrows(IllegalStateException.class, executable);
        assertEquals(expected, ex.getMessage());
    }

    @Test
    void transferTestNotActivated() {
        BankAccount account = new BankAccount("a", "b");
        BankAccount account2 = new BankAccount("c", "d");
        String expected = "Один из счетов не активирован";

        Executable executable = () -> account.transfer(account2, 2000);

        IllegalStateException ex = assertThrows(IllegalStateException.class, executable);
        assertEquals(expected, ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBlockedOtherAccount() {
        BankAccount account = new BankAccount("a", "b");
        BankAccount account2 = new BankAccount("c", "d");
        account.activate("KZT");
        account.deposit(5000);
        account.block();
        account2.block();
        String expected = "Один из счетов заблокирован";

        Executable executable = () -> account.transfer(account2, 2000);

        IllegalStateException ex = assertThrows(IllegalStateException.class, executable);
        assertEquals(expected, ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenInsufficientBalanceOtherAccount() {
        BankAccount account = new BankAccount("a", "b");
        BankAccount account2 = new BankAccount("c", "d");
        account.activate("KZT");
        account.deposit(5000);
        String expected = "Нельзя снять больше чем есть на счету";

        Executable executable = () -> account.transfer(account2, 6000);

        IllegalStateException ex = assertThrows(IllegalStateException.class, executable);
        assertEquals(expected, ex.getMessage());
    }


}