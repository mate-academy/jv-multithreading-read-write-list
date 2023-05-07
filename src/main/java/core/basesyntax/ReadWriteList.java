package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock writeLock = lock.writeLock();
    private final Lock readLock = lock.readLock();

    public void add(E element) {
        try {
            writeLock.lock();
            list.add(element);
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int index) {
        try {
            readLock.lock();
            return list.get(index);
        } finally {
            readLock.unlock();
        }
    }

    public int size() {
        try {
            readLock.lock();
            return list.size();
        } finally {
            readLock.unlock();
        }
    }
}
