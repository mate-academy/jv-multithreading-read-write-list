package core.basesyntax.thread;

import core.basesyntax.ReadWriteList;
import java.util.Random;

public class Writer implements Runnable {
    private final ReadWriteList<Integer> sharedList;

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
