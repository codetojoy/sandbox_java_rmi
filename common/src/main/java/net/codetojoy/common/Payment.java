
package net.codetojoy.common;

import java.io.Serializable;

public class Payment implements Serializable {
    private final String accountId;
    private final int amount;

    public Payment(String accountId, int amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getName() { return accountId; }
    public int getAmount() { return amount; }

    public String toString() {
        String result =  " accountId: " + accountId + " amount: " + amount;
        return result;
    }
}
