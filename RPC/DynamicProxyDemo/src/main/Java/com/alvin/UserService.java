package com.alvin;


import com.alvin.annotation.Log;

public interface UserService {

    @Log
    int addUser(String name);
}
