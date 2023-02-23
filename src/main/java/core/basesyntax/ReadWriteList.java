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
        // write your code here
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            list.add(element);
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int index) {
        // write your code here
        E e = null;
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            e = list.get(index);
        } catch (IndexOutOfBoundsException exc) {
            System.out.println("Wrong index");
        } finally {
            readLock.unlock();
        }
        return e;
    }

    public int size() {
        // write your code here
        return 0;
    }
}
