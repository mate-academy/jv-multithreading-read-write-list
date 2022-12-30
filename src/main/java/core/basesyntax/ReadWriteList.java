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
        Lock lockWrite = lock.writeLock();
        lockWrite.lock();
        try {
            list.add(element);
        } finally {
            lockWrite.unlock();
        }
    }

    public E get(int index) {
        Lock lockRead = lock.readLock();
        lockRead.lock();
        try {
            return list.get(index);
        } finally {
            lockRead.unlock();
        }
    }

    public int size() {
        Lock lockRead = lock.readLock();
        lockRead.lock();
        try {
            return list.size();
        } finally {
            lockRead.unlock();
        }
    }
}
