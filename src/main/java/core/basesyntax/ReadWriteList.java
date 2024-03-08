package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(E element) {
        readWriteLock.writeLock().lock();
        try {
            list.add(element);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public E get(int index) {
        readWriteLock.readLock().lock();
        try {
            return list.get(index);
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public int size() {
        return list.size();
    }
}
