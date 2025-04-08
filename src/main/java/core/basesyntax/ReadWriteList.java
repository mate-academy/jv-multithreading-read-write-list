package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private final List<E> list = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(E element) {
        lock.writeLock().lock(); // блокування для запису
        try {
            list.add(element);
        } finally {
            lock.writeLock().unlock(); // розблокування
        }
    }

    public E get(int index) {
        lock.readLock().lock(); // блокування для читання
        try {
            return list.get(index);
        } finally {
            lock.readLock().unlock(); // розблокування
        }
    }

    public int size() {
        lock.readLock().lock(); // блокування для читання
        try {
            return list.size();
        } finally {
            lock.readLock().unlock(); // розблокування
        }
    }
}
