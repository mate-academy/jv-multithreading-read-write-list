package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private final List<E> list = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(E element) {
        if (element != null) {
            try {
                lock.writeLock().lock();
                list.add(element);
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    public E get(int index) {
        if (index >= 0) {
            try {
                lock.readLock().lock();
                return list.get(index);
            } finally {
                lock.readLock().unlock();
            }
        } else {
            throw new RuntimeException("Index is lower than 0. Index: " + index);
        }
    }

    public int size() {
        try {
            lock.readLock().lock();
            return list.size();
        } finally {
            lock.readLock().unlock();
        }
    }
}
