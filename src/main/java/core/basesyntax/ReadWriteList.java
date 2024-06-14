package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(E element) {
        lock.readLock().lock();
        try {
            list.add(element);
        } finally {
            lock.readLock().unlock();
        }
    }

    public E get(int index) {
        lock.writeLock().lock();
        try {
            return list.get(index);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        return list.size();
    }
}
