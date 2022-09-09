package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private final List<E> list = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock writeLock = lock.writeLock();

    public void add(E element) {
        writeLock.lock();
        try {
            list.add(element);
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int index) {
        writeLock.lock();
        try {
            return list.get(index);
        } finally {
            writeLock.unlock();
        }
    }

    public int size() {
        writeLock.lock();
        try {
            return list.size();
        } finally {
            writeLock.unlock();
        }
    }
}
