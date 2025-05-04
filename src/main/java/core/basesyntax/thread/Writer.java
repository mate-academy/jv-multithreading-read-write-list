package main.java.core.basesyntax.thread;

import java.util.Random;
import main.java.core.basesyntax.ReadWriteList;

public class Writer implements Runnable {
    private ReadWriteList<Integer> sharedList;

    public Writer(ReadWriteList<Integer> sharedList) {
        this.sharedList = sharedList;
    }

    @Override
    public void run() {
        int number = new Random().nextInt(100);
        sharedList.add(number);
        System.out.println(Thread.currentThread().getName() + " -> write: " + number);
    }
}
