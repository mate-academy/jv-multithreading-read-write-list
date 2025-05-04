package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock writeLock = lock.writeLock();
    private final Lock readLock = lock.readLock();

    public void add(E element) {
        writeLock.lock();
        try {
            list.add(element);
        } finally {
            writeLock.lock();
        }
    }

    public E get(int index) {
        readLock.lock();
        try {
            list.get(index);
        } finally {
            readLock.unlock();
        }
        return null;
    }

    public int size() {
        readLock.lock();
        try {
            return list.size();
        } finally {
            readLock.unlock();
        }
    }
}
