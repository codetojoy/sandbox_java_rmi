
package net.codetojoy.server; 

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.codetojoy.common.*;
import net.codetojoy.common.rmi.*;

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
    public long ping() {
        return System.currentTimeMillis();
    } 

    @Override
    public CompoundInfo getCompoundInfo(String name) {
        System.out.println("TRACER name: " + name + " request #" + count++);

        User user = userService.getUser(name);
        Payment payment = billingService.getPayment(name);

        CompoundInfo result = new CompoundInfo(payment, user);
        return result;
    }

    public static boolean lookForServices(String... targetServices) {
        boolean result = false;
        int numFound = 0;
        int numTargets = targetServices.length;

        RegistryReader registryReader = new RegistryReader();
        String[] services = registryReader.readRegistry();

        if (services != null) {
            for (String service : services) {
                for (String targetService : targetServices) {
                    if (service.equals(targetService)) {
                        numFound++;
                        System.out.println("TRACER " + service + " found. that is " + numFound + " of " + numTargets);
                        break;
                    }
                }
            } 
        }

        result = (numFound == numTargets);

        return result;
    }

    public static void waitForServices(String... services) {
        int maxAttempts = 30;
        long delay = 4*1000;
        boolean done = false;
        int attemptCount = 0;

        while (! done) {
            boolean isReady = lookForServices(services);

            if (isReady) {
                done = true;
            } else {
                System.out.println("not ready ... sleeping # " + attemptCount);
                try { Thread.sleep(delay); } catch (Exception e) {}
            }

            attemptCount++;

            if (attemptCount > maxAttempts) {
                System.out.println("max attempts! giving up ...");
                System.exit(-5150);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("\n\nTRACER: CompoundService starting up...");
        waitForServices(Constants.USER_SERVICE, Constants.BILLING_SERVICE);

        ApplicationContext context = new ClassPathXmlApplicationContext("server_config.xml");

        context.getBean("compoundServiceExporter");

        System.out.println("\n\nTRACER: CompoundService ready !");
    }
}
