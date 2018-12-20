package com.alvin;

public class userServiceImpl implements UserService {
    @Override
    public int addUser(String name) {
        System.out.println("add user " + name);
        return  1;
    }
}
