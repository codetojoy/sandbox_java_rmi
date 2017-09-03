
package net.codetojoy.common;

import java.io.Serializable;

public class Payment implements Serializable {
    private final String name;
    private final String accountId;
    private final int amount;

    public Payment(String name, String accountId, int amount) {
        this.name = name;
        this.accountId = accountId;
        this.amount = amount;
    }

    public String toString() {
        String result =  "payment: {name: " + name + ", accountId: " + accountId + " , amount: " + amount + " }";
        return result;
    }
}
