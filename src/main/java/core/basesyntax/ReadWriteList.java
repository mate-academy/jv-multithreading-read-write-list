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
        Lock writer = lock.writeLock();
        writer.lock();
        try {
            list.add(element);
        } finally {
            writer.unlock();
        }
    }

    public E get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }
}
