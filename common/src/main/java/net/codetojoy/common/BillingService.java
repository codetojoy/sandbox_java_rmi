
package net.codetojoy.common;

public interface BillingService {
    Payment getPayment(String name);

    long ping();
}
