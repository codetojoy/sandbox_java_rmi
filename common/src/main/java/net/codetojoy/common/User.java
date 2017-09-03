
package net.codetojoy.common;

import java.io.Serializable;

public class User implements Serializable {
    private final String name;
    private final int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() { return name; }
    public int getId() { return id; }

    public String toString() {
        String result =  " name: " + name + " id: " + id;
        return result;
    }
}
