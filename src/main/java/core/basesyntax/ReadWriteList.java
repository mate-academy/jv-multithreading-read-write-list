package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(E element) {
        lock.writeLock().lock();
        lock.readLock().lock();
        try {
            list.add(element);
        } finally {
            lock.writeLock().unlock();
            lock.readLock().unlock();
        }
    }

    public E get(int index) {
        return list.get(index);
    }

    public int size() {
        int size;
        lock.writeLock().lock();
        try {
            size = list.size();
        } finally {
            lock.writeLock().unlock();
        }
        return size;
    }
}
