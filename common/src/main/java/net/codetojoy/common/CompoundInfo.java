
package net.codetojoy.common;

import java.io.Serializable;

public class CompoundInfo implements Serializable {
    private final Payment payment;
    private final User user;

    public CompoundInfo(Payment payment, User user) {
        this.payment = payment;
        this.user = user;
    }

    public Payment getPayment() { return payment; }
    public User getUser() { return user; }

    public String toString() {
        String result =  " payment: " + payment.toString() + " user: " + user.toString();
        return result;
    }
}
