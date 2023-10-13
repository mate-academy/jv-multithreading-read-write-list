package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private final List<E> list = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(E element) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            list.add(element);
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int index) {
        E element;
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            element = list.get(index);
        } finally {
            readLock.unlock();
        }
        return element;
    }

    public int size() {
        int size;
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            size = list.size();
        } finally {
            readLock.unlock();
        }
        return size;
    }
}
