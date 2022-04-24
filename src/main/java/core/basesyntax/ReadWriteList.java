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
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            list.add(element);
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int index) {
        Lock readLock = lock.readLock();
        E element;

        readLock.tryLock();
        try {
            element = list.get(index);
        } finally {
            readLock.unlock();
        }

        return element;
    }

    public int size() {
        Lock readLock = this.lock.readLock();
        int size;

        readLock.tryLock();
        try {
            size = list.size();
        } finally {
            readLock.unlock();
        }

        return size;
    }
}
