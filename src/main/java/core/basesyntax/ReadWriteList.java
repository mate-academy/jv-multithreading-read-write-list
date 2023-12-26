package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private static final String EXCEPTION = "Index is incorrect";
    private static final Integer ONE = 1;
    private static final Integer ZERO = 0;
    private List<E> list = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public void add(E element) {
        writeLock.lock();
        try {
            list.add(element);
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int index) {
        readLock.lock();
        try {
            if (index - ONE > size() || index < ZERO) {
                throw new RuntimeException(EXCEPTION);
            }
            return list.get(index);
        } finally {
            readLock.unlock();
        }
    }

    public int size() {
        return list.size();
    }
}
