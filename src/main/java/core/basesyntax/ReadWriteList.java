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
        Lock w = lock.writeLock();
        w.lock();
        try {
            list.add(element);
        } finally {
            w.unlock();
        }
    }

    public E get(int index) {
        Lock r = lock.readLock();
        r.lock();
        try {
            return list.get(index);
        } finally {
            r.unlock();
        }
    }

    public int size() {
        Lock r = lock.readLock();
        r.lock();
        try {
            return list.size();
        } finally {
            r.unlock();
        }
    }
}
