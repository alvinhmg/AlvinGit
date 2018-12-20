package com.alvin;

public class RealSubject implements Subject {

    @Override
    public void rent() {
        System.out.println(" i want to rent a house");
    }

    @Override
    public void hello(String string) {
        System.out.println("hello" + string);
    }
}
