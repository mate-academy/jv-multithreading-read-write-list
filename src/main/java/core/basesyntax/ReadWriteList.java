package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(E element) {
        // write your code here
        Lock writeLock = lock.writeLock();
        Lock readLock = lock.readLock();

        writeLock.lock();
        readLock.lock();

        try {
            list.add(element);
        } finally {
            writeLock.unlock();
            readLock.unlock();
        }
    }

    public E get(int index) {
        // write your code here
        return list.get(index);
    }

    public int size() {
        // write your code here
        return list.size();
    }
}
