package com.openhome.lambda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class LineStartsWith2 {

    /**
     * 若使用earlierAns, 速度會較快, 因Stream的關係
     * 因為lazy evaluation, 在要findFirst才會執行filter
     * 若是answer, 則速度不會比較快甚至可能會比LineStartsWith慢一些ms
     */
    public static void main(String[] args) throws IOException {
        var answer = "check this!";
        var earlierAns = "this is earlier answer";
        var start = System.currentTimeMillis();
        Optional<String> result = Files.lines(Path.of("src/main/resources/fileStart.txt"))
                        .filter(line -> line.startsWith(answer))
                        .findFirst();

        System.out.println(result.orElse("not matched line") +
                ", cost " + (System.currentTimeMillis() - start) + " ms");
    }
}
