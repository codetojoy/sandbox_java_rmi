
package net.codetojoy.common;

import java.io.Serializable;

public class User implements Serializable {
    private final String name;
    private final int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String toString() {
        String result =  "user: {name: " + name + ", id: " + id + " }";
        return result;
    }
}
