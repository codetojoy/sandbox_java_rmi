
package net.codetojoy.server; 

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.codetojoy.common.User;
import net.codetojoy.common.UserService;

import java.util.concurrent.ThreadLocalRandom;

public class UserServiceImpl implements UserService {
    // not thread-safe
    private static int count = 0;

    @Override
    public User getUser(String name) {
        System.out.println("TRACER request name: " + name + " total # " + count++);
        int id = getRandomId();
        User user = new User(name, id);
        return user;
    }

    private int getRandomId() {
        final int min = 100;
        final int max = 1000;
        final int result = ThreadLocalRandom.current().nextInt(min, max + 1);
        return result;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("server_config.xml");

        context.getBean("userService");
        System.out.println("TRACER: User Service ready");
    }
}
