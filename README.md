# Read-write list

Implement methods in `ReadWriteList` class so that multiple threads could read data at the same time
as long as there’s no thread that is updating the data. Only one thread can update the data at a time
causing other threads (both readers and writers) to block until the update is over.

Use `ReadWriteLock` implementation for this purpose. 

In your solution you should lock and unlock `read` and `write locks` in order to fulfill 
the conditions above.
Remember to use `try-finally` construct - you want to release the lock 
even if the operation with the underlying `list` was not successful.

You can run `main()` to check your solution.

