package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private final List<E> list = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(E element) {
        Lock writer = lock.writeLock();
        writer.lock();
        try {
            list.add(element);
        } finally {
            writer.unlock();
        }
    }

    public E get(int index) {
        Lock reader = lock.readLock();
        reader.lock();
        try {
            return list.get(index);
        } finally {
            reader.unlock();
        }
    }

    public int size() {
        Lock reader = lock.readLock();
        reader.lock();
        try {
            return list.size();
        } finally {
            reader.unlock();
        };
    }
}
