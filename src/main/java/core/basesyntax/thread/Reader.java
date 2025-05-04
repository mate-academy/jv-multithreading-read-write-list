package core.basesyntax.thread;

import core.basesyntax.ReadWriteList;
import java.util.concurrent.ThreadLocalRandom;

public class Reader implements Runnable {
    private ReadWriteList<Integer> sharedList;

    public Reader(ReadWriteList<Integer> sharedList) {
        this.sharedList = sharedList;
    }

    @Override
    public void run() {
        int size = sharedList.size();
        if (size > 0) {
            Integer number = sharedList.get(ThreadLocalRandom.current().nextInt(size));
            System.out.println(Thread.currentThread().getName() + " -> read: " + number);
        }
    }
}
