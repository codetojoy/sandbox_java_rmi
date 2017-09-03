
package net.codetojoy.client;

import org.springframework.context.*;
import org.springframework.context.support.*;

import net.codetojoy.common.*;

import net.codetojoy.common.rmi.RegistryReader;

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

    private void processCommand() {
        Prompt prompt = new Prompt();
        String input = prompt.getInput("\n\ncmd: [U=user, B=billing, C=compound, R=registry, Q=quit] ?", USER, BILLING, COMPOUND, REGISTRY);

        if (input.equalsIgnoreCase(USER)) {
            String name = prompt.getInput("enter a name: "); 
            User user = getUserService().getUser(name);
            System.out.println("result : " + user);
        } else if (input.equalsIgnoreCase(BILLING)) {
            String name = prompt.getInput("enter a name: "); 
            Payment payment = getBillingService().getPayment(name);
            System.out.println("result : " + payment);
        } else if (input.equalsIgnoreCase(COMPOUND)) {
            String name = prompt.getInput("enter a name: "); 
            CompoundInfo info = getCompoundService().getCompoundInfo(name);
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

    public void inputLoop() {
        while (true) {
            try {
                processCommand();
            } catch(Exception ex) {
                System.err.println("\nTRACER command failed! check if the service is running \n");
            }
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("client_config.xml"); 
        Client client = new Client(context);
        client.inputLoop();
    }
}
