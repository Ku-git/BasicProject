package com.openhome.lambda;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LineStartsWith {


    public static void main(String[] args) throws IOException {

        var answer = "check this!";
        var earlierAns = "this is earlier answer";
        var result = "not matched line";
        var start = System.currentTimeMillis();
        for(var line: Files.readAllLines(Path.of("src/main/resources/fileStart.txt"))) {
            if(line.startsWith(answer)) {
                result = line;
                break;
            }
        }
        System.out.println(result + ", cost " + (System.currentTimeMillis() - start) + " ms");
    }

    /**
     * 測試資料
     */
    private static void fillTxtFile() {
        File file = new File("src/main/resources/fileStart.txt");
        if(!file.exists()) {
            System.out.println("not exists");
            return;
        }

        try(FileWriter fw = new FileWriter(file)) {
            for(int i = 0; i < 10000; i++) {
                if(i == 10) {
                    fw.write("this is earlier answer" + "\n");
                    fw.flush();
                    continue;
                }
                if(i == 9700) {
                    fw.write("check this! this is the start with check answer" + "\n");
                    fw.flush();
                    continue;
                }
                fw.write("this is example for start with" + "\n");
                fw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
