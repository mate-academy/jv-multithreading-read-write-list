package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private static int size;
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
        size++;
    }

    public E get(int index) {
        return list.get(index);
    }

    public int size() {
        return size;
    }
}
