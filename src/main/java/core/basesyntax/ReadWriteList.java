package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private final List<E> list = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final AtomicInteger size = new AtomicInteger();

    public void add(E element) {
        try {
            lock.writeLock().lock();
            list.add(element);
            size.getAndIncrement();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public E get(int index) {
        try {
            lock.readLock().lock();
            return list.get(index);
        } finally {
            lock.readLock().unlock();
        }
    }

    public int size() {
        return size.get();
    }
}
