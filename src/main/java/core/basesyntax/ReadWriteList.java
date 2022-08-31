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
        Lock wr = lock.writeLock();
        wr.lock();
        try {
            list.add(element);
        } finally {
            wr.unlock();
        }
    }

    public E get(int index) {
        Lock rr = lock.readLock();
        rr.lock();
        try {
            return list.get(index);
        } finally {
            rr.unlock();
        }
    }

    public int size() {
        return list.size();
    }
}
