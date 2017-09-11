
package net.codetojoy.monitor; 

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.codetojoy.common.*;

import java.util.*;
import java.util.concurrent.*;

import java.rmi.registry.*;

class Task implements Runnable {
    private ApplicationContext context;
 
    private static final String BILLING_SERVICE = "billingService";
    private static final String COMPOUND_SERVICE = "compoundService";
    private static final String USER_SERVICE = "userService";

    private final Set ALL_ENTRIES = new HashSet<>();

    Task(ApplicationContext context) {
        this.context = context; 

        ALL_ENTRIES.add(BILLING_SERVICE);
        ALL_ENTRIES.add(COMPOUND_SERVICE);
        ALL_ENTRIES.add(USER_SERVICE);
    }

    private void ping(String serviceName) {
        try {
            String result = "N/A";

            if (serviceName.equals(USER_SERVICE)) {
                UserService userService = (UserService) context.getBean(serviceName);
                long pingResult = userService.ping();
                result = new Date(pingResult).toString();
            } else if (serviceName.equals(BILLING_SERVICE)) {
                BillingService billingService = (BillingService) context.getBean(serviceName);
                long pingResult = billingService.ping();
                result = new Date(pingResult).toString();
            } else if (serviceName.equals(USER_SERVICE)) {
                CompoundService compoundService = (CompoundService) context.getBean(serviceName);
                long pingResult = compoundService.ping();
                result = new Date(pingResult).toString();
            } 

            System.out.println("TRACER from " + serviceName + " : " + result);
        } catch(Exception ex) {
            System.err.println("TRACER caught ex: " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("--------------------------");
            System.out.println("\n\nTRACER " + new Date() + " checking...");
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 2020);
            String[] entries = registry.list();

            if (entries != null) {
                Set<String> refSet = new HashSet(ALL_ENTRIES); 
                Set<String> entrySet = new HashSet<String>(Arrays.asList(entries));
                refSet.removeAll(entrySet);

                if (! refSet.isEmpty()) {
                    for (String entry : refSet) {
                        System.out.println("\nTRACER found entry: " + entry); 
                        ping(entry);
                    }
                }
            } else {
                System.out.println("TRACER no entries found");
            } 
        } catch (Exception ex) {
            System.err.println("TRACER caught exception: " + ex.getMessage());
        }
    }
}

public class Monitor {
    private ApplicationContext context;
    private ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(1);

    Monitor(ApplicationContext context) {
        this.context = context;
    }

    public void start() {
        final int delayInSeconds = 4;
        scheduledPool.scheduleAtFixedRate(new Task(context), delayInSeconds, delayInSeconds, TimeUnit.SECONDS);
    }
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("server_config.xml");
        Monitor monitor = new Monitor(context);
        monitor.start();

        while (true) {
            try {  Thread.sleep(30*1000); } catch (Exception ex) {}
        }
    }
}
