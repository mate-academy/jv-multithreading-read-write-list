package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private int size;

    public void add(E element) {
        // write your code here
        lock.writeLock().lock();
        try {
            size++;
            list.add(element);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public E get(int index) {
        // write your code here
        E element;
        lock.readLock().lock();
        try {
            element = list.get(index);
        } finally {
            lock.readLock().unlock();
        }
        return element;
    }

    public int size() {
        // write your code here
        lock.readLock().lock();
        try {
            return size;
        } finally {
            lock.readLock().unlock();
        }
    }
}
