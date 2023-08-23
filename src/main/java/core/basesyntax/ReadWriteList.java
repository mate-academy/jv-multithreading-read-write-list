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
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            list.add(element);
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int index) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        E element;
        try {
            element = list.get(index);
        } finally {
            writeLock.unlock();
        }
        return element;
    }

    public int size() {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        int size;
        try {
            size = list.size();
        } finally {
            writeLock.unlock();
        }
        return size;
    }
}
