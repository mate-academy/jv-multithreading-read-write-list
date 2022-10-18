package core.basesyntax.thread;

import core.basesyntax.ReadWriteList;
import java.util.Random;

public class Reader implements Runnable {
    private final ReadWriteList<Integer> sharedList;

    public Reader(ReadWriteList<Integer> sharedList) {
        this.sharedList = sharedList;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - Start of read method ...");
        Random random = new Random();
        int size = sharedList.size();
        if (size > 0) {
            Integer number = sharedList.get(random.nextInt(size));
            System.out.println(Thread.currentThread().getName() + " -> read: " + number);
        }
        System.out.println(Thread.currentThread().getName() + " - ... End of read method");
    }
}
