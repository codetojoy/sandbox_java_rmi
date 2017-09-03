
package net.codetojoy.server; 

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.codetojoy.common.*;

import java.util.concurrent.ThreadLocalRandom;

public class CompoundServiceImpl implements CompoundService {
    private static int count = 0;

    private UserService userService;
    private BillingService billingService;

    public UserService getUserService() { return userService; }
    public BillingService getBillingService() { return billingService; }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setBillingService(BillingService billingService) {
        this.billingService = billingService;
    }

    @Override
    public CompoundInfo getCompoundInfo(String name) {
        User user = userService.getUser(name);
        Payment payment = billingService.getPayment(5150);
        System.out.println("TRACER request #" + count++);
        CompoundInfo result = new CompoundInfo(payment, user);
        return result;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("server_config.xml");

        context.getBean("compoundService");
        System.out.println("TRACER: CompoundService ready");
    }
}
