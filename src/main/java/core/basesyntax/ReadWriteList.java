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
        Lock lock = this.lock.writeLock();
        lock.lock();
        try {
            list.add(element);
        } finally {
            lock.unlock();
        }
    }

    public E get(int index) {
        Lock lock = this.lock.readLock();
        lock.lock();
        try {
            return list.get(index);
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        Lock lock = this.lock.readLock();
        lock.lock();
        try {
            return list.size();
        } finally {
            lock.unlock();
        }
    }
}
