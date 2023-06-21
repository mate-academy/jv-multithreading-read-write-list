package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(E element) {
        try {
            lock.writeLock().lock();
            lock.readLock().lock();
            list.add(element);
        } finally {
            lock.writeLock().unlock();
            lock.readLock().unlock();
        }
    }

    public E get(int index) {
        try {
            lock.writeLock().lock();
            return list.get(index);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        try {
            lock.writeLock().lock();
            return list.size();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
