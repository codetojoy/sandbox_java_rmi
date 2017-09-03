
package net.codetojoy.server; 

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.codetojoy.common.User;
import net.codetojoy.common.UserService;

import net.codetojoy.common.util.Utils;

public class UserServiceImpl implements UserService {
    // not thread-safe
    private static int count = 0;

    @Override
    public User getUser(String name) {
        System.out.println("TRACER request name: " + name + " total # " + count++);

        int id = new Utils().getRandomId(100, 1000);

        User user = new User(name, id);
        return user;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("server_config.xml");

        context.getBean("userService");
        System.out.println("\n\nTRACER: User Service ready !");
    }
}
