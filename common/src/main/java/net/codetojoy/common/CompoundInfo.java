
package net.codetojoy.common;

import java.io.Serializable;

public class CompoundInfo implements Serializable {
    private final Payment payment;
    private final User user;

    public CompoundInfo(Payment payment, User user) {
        this.payment = payment;
        this.user = user;
    }

    public String toString() {
        String result =  "compound: \n" + payment.toString() + "\n" + user.toString();
        return result;
    }
}
