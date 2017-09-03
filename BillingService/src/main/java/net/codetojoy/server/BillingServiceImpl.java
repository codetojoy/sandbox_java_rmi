
package net.codetojoy.server; 

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.codetojoy.common.Payment;
import net.codetojoy.common.BillingService;

import java.util.concurrent.ThreadLocalRandom;

public class BillingServiceImpl implements BillingService {
    // not thread-safe
    private static int count = 0;

    @Override
    public Payment getPayment(int userId) {
        System.out.println("TRACER request userId: " + userId + " total #" + count++);
        String accountId = "acc-" + getRandomId(2000,3000);
        int amount = getRandomId(30,90);
        Payment payment = new Payment(accountId, amount);
        return payment;
    }

    private int getRandomId(int min, int max) {
        final int result = ThreadLocalRandom.current().nextInt(min, max + 1);
        return result;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("server_config.xml");

        context.getBean("billingService");
        System.out.println("TRACER: Billing Service ready");
    }
}
