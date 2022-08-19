package core.basesyntax;

import core.basesyntax.thread.Reader;
import core.basesyntax.thread.Writer;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ReadWriteList<Integer> list = new ReadWriteList<>(new ArrayList<>());

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
