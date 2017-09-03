
package net.codetojoy.client;

import org.springframework.context.*;
import org.springframework.context.support.*;

import net.codetojoy.common.*;

import java.util.*;

public class Client {
    private static final String BILLING = "b";
    private static final String COMPOUND = "c";
    private static final String REGISTRY = "r";
    private static final String USER = "u";

    private static final String BILLING_SERVICE = "billingService";
    private static final String COMPOUND_SERVICE = "compoundService";
    private static final String USER_SERVICE = "userService";

    private ApplicationContext context = null;

    public Client(ApplicationContext context) {
        this.context = context;
    }

    public BillingService getBillingService() {
        return (BillingService) context.getBean(BILLING_SERVICE);
    }

    public CompoundService getCompoundService() {
        return (CompoundService) context.getBean(COMPOUND_SERVICE);
    }

    public UserService getUserService() {
        return (UserService) context.getBean(USER_SERVICE);
    }

    public void inputLoop(UserService userService, 
                          BillingService billingService, 
                          CompoundService compoundService) {
        Prompt prompt = new Prompt();

        while (true) {
            String input = prompt.getInput("cmd: [U=user, B=billing, C=compound, R=registry, Q=quit] ?", USER, BILLING, COMPOUND, REGISTRY);
            if (input.equalsIgnoreCase(USER)) {
                String name = prompt.getInput("enter a name: "); 
                User user = userService.getUser(name);
                System.out.println("result : " + user);
            } else if (input.equalsIgnoreCase(BILLING)) {
                System.out.println("using a fake user id ...");
                int userId = 5150;
                Payment payment = billingService.getPayment(userId);
                System.out.println("result : " + payment);
            } else if (input.equalsIgnoreCase(COMPOUND)) {
                System.out.println("using a fake id ...");
                String name = "fakeUser";
                CompoundInfo info = compoundService.getCompoundInfo(name);
                System.out.println("result : " + info);
            } else if (input.equalsIgnoreCase(REGISTRY)) {
                String[] results = new RegistryReader().readRegistry();
                System.out.println("TRACER registry results: ");
                if (results != null) {
                    for (String result : results) {
                        System.out.println("TRACER reg: " + result);
                    }
                }
            } 
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("client_config.xml"); 
        Client client = new Client(context);

        UserService userService = client.getUserService();
        BillingService billingService = client.getBillingService();
        CompoundService compoundService = client.getCompoundService();

        client.inputLoop(userService, billingService, compoundService);
    }
}
