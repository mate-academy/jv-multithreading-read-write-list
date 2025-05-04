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
        if (writeLock.tryLock()) {
            try {
                list.add(element);
            } finally {
                writeLock.unlock();
            }
        }
    }

    public E get(int index) {
        Lock readLock = lock.readLock();
        if (readLock.tryLock()) {
            try {
                return list.get(index);
            } finally {
                readLock.unlock();
            }
        }
        throw new RuntimeException("Can't find element by index: " + index);
    }

    public int size() {
        Lock readeLock = lock.readLock();
        if (readeLock.tryLock()) {
            try {
                return list.size();
            } finally {
                readeLock.unlock();
            }
        }
        throw new RuntimeException("Can't get list size.");
    }
}
