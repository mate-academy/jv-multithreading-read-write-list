package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock writeLock = lock.writeLock();
    private Lock readLock = lock.readLock();

    public void add(E element) {
        writeLock.lock();
        try {
            list.add(element);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public E get(int index) {
        readLock.lock();
        try {
            return list.get(index);
        } finally {
            lock.readLock().unlock();
        }
    }

    public int size() {
        readLock.lock();
        return list.size();
    }
}
