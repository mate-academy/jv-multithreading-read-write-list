package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private final List<E> list = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock rLock = lock.readLock();
    private final Lock wLock = lock.writeLock();

    public void add(E element) {
        // write your code here
        wLock.lock();
        try {
            list.add(element);
        } finally {
            wLock.unlock();
        }
    }

    public E get(int index) {
        // write your code here
        rLock.lock();
        try {
            return list.get(index);
        } finally {
            rLock.unlock();
        }
    }

    public int size() {
        // write your code here
        rLock.lock();
        try {
            return list.size();
        } finally {
            rLock.unlock();
        }
    }
}
