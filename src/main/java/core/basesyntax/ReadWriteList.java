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
        Lock lockForAdd = lock.writeLock();
        lockForAdd.lock();
        try {
            list.add(element);
        } finally {
            lockForAdd.unlock();
        }
    }

    public E get(int index) {
        Lock lockForGet = lock.readLock();
        lockForGet.lock();
        try {
            return list.get(index);
        } finally {
            lockForGet.unlock();
        }
    }

    public int size() {
        Lock lockForSize = lock.readLock();
        lockForSize.lock();
        try {
            return list.size();
        } finally {
            lockForSize.unlock();
        }
    }
}
