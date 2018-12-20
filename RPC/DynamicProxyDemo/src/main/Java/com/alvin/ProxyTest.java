package com.alvin;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        UserService userService = new userServiceImpl();
        userService = (UserService) LogProxy.factory(userService);
        userService.addUser("alvin");

    }
}
