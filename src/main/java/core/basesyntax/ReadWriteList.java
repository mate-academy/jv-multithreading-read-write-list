package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private final List<E> list = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(E element) {
        this.lock.writeLock().lock();

        try {
            this.list.add(element);
        } finally {
            lock.writeLock().unlock();
        }

    }

    public E get(int index) {
        this.lock.readLock().lock();

        try {
            return this.list.get(index);
        } finally {
            this.lock.readLock().unlock();
        }

    }

    public int size() {
        this.lock.readLock().lock();

        try {
            return this.list.size();
        } finally {
            this.lock.readLock().unlock();
        }
    }
}
