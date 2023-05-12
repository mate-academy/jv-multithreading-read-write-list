package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readerLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void add(E element) {
        writeLock.lock();
        try {
            list.add(element);
        } finally {
            writeLock.unlock();
        }

    }

    public E get(int index) {
        readerLock.lock();
        try {
            return list.get(index);
        } finally {
            readerLock.unlock();
        }
    }

    public int size() {
        return list.size();
    }
}
