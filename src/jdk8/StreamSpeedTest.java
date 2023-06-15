package jdk8;

import java.util.stream.IntStream;

public class StreamSpeedTest {
    public static void main(String[] args) {
        long t0 = System.nanoTime();

        //serial
        int a[] = IntStream.range(0, 100_000_000).filter(p -> p % 2 == 0).toArray();

        long t1 = System.nanoTime();

        //parallel
        int b[] = IntStream.range(0, 100_000_000).parallel().filter(p -> p % 2 == 0).toArray();

        long t2 = System.nanoTime();

        //serial: 0.06s, parallel 0.02s，parallel faster than serial
        System.out.printf("serial: %.2fs, parallel %.2fs%n", (t1 - t0) * 1e-9, (t2 - t1) * 1e-9);

    }
}
