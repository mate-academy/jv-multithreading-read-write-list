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
        Lock writerLock = lock.writeLock();
        writerLock.lock();
        try {
            list.add(element);
        } finally {
            writerLock.unlock();
        }
    }

    public E get(int index) {
        Lock readerLock = lock.readLock();
        readerLock.lock();
        try {
            return list.get(index);
        } finally {
            readerLock.unlock();
        }
    }

    public int size() {
        Lock readerLock = lock.readLock();
        readerLock.lock();
        try {
            return list.size();
        } finally {
            readerLock.unlock();
        }
    }
}
