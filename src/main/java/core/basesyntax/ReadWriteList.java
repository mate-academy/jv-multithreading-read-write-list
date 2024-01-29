package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private int size;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public ReadWriteList() {
        this.list = new ArrayList<>();
        size = 0;
    }

    public void add(E element) {
        lock.writeLock().lock();
        try {
            list.add(element);
            size++;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public E get(int index) {
        lock.readLock().lock();
        try {
            return list.get(index);
        } finally {
            lock.readLock().unlock();
        }
    }

    public int size() {
        return size;
    }
}
