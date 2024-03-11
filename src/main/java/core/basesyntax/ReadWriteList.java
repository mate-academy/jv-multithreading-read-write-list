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
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            list.add(element);
        } finally {
            writeLock.unlock();
        }
    }

    public E get(int index) {
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            return list.get(index);
        } finally {
            readLock.unlock();
        }
    }

    public int size() {
        return list.size();
    }
}
/**
 * # Read-write list
 *
 * Implement methods in `ReadWriteList` class so that multiple threads could read data at the same time
 * as long as there’s no thread that is updating the data. Only one thread can update the data at a time
 * causing other threads (both readers and writers) to block until the update is over.
 *
 * Use `ReadWriteLock` implementation for this purpose.
 *
 * In your solution you should lock and unlock `read` and `write locks` in order to fulfill
 * the conditions above.
 * Remember to use `try-finally` construct - you want to release the lock
 * even if the operation with the underlying `list` was not successful.
 *
 * You can run `main()` to check your solution.
 */
