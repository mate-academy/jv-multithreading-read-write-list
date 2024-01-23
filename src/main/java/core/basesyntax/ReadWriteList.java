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
        Lock lock2 = lock.writeLock();
        lock2.lock();
        try {
            list.add(element);
        } finally {
            lock2.unlock();
        }
        // write your code here
    }

    public E get(int index) {
        Lock lock1 = lock.readLock();
        lock1.lock();
        try {
            return list.get(index);
        } finally {
            lock1.unlock();
        }
        // write your code here
    }

    public int size() {
        // write your code here
        return list.size();
    }
}
