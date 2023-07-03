package main.java.core.basesyntax;

import main.java.core.basesyntax.thread.Reader;
import main.java.core.basesyntax.thread.Writer;

public class Main {
    public static void main(String[] args) {
        ReadWriteList<Integer> list = new ReadWriteList<>();

        Writer writer = new Writer(list);
        Reader reader = new Reader(list);

        for (int i = 0; i < 5; i++) {
            new Thread(writer).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(reader).start();
        }
    }
}
