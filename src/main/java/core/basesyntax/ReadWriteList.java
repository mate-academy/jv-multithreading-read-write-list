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
        Lock write = lock.writeLock();
        write.lock();
        try {
            list.add(element);
        } finally {
            write.unlock();
        }
    }

    public E get(int index) {
        Lock read = lock.readLock();
        read.lock();
        try {
            return list.get(index);
        } finally {
            read.unlock();
        }
    }

    public int size() {
        Lock read = lock.readLock();
        read.lock();
        try {
            return list.size();
        } finally {
            read.unlock();
        }
    }
}
