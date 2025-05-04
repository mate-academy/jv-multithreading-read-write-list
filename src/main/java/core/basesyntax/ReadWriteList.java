package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteList<E> {
    private List<E> list = new ArrayList<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void add(E element) {
        Lock lock = readWriteLock.writeLock();
        lock.lock();
        try {
            list.add(element);
        } finally {
            lock.unlock();
        }
        // write your code here
    }

    public E get(int index) {
        Lock lock = readWriteLock.readLock();
        lock.lock();
        E result;
        try {
            result = list.get(index);
        } finally {
            lock.unlock();
        }
        // write your code here
        return result;
    }

    public int size() {
        Lock lock = readWriteLock.readLock();
        lock.lock();
        int result;
        try {
            result = list.size();
        } finally {
            lock.unlock();
        }
        // write your code here
        return result;
    }
}
