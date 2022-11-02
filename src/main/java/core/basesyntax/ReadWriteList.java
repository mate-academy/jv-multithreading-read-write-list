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
        Lock addLock = lock.writeLock();
        addLock.lock();
        try {
            list.add(element);
        } finally {
            addLock.unlock();
        }
    }

    public E get(int index) {
        Lock addLock = lock.readLock();
        addLock.lock();
        try {
            return list.get(index);
        } finally {
            addLock.unlock();
        }
    }

    public int size() {
        Lock addLock = lock.readLock();
        addLock.lock();
        try {
            return list.size();
        } finally {
            addLock.unlock();
        }
    }
}
