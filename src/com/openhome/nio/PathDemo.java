package com.openhome.nio;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

public class PathDemo {

    public static void main(String[] args) {
        Path path = Paths.get(System.getProperty("user.home"), "Documents", "Downloads");

        System.out.println("toString: " + path.toAbsolutePath());
        System.out.println("getFileName: " + path.getFileName());
        System.out.println("getName(0): " + path.getName(0));
        System.out.println("getNameCount: " + path.getNameCount());
        System.out.println("subpath(0, 2): " + path.subpath(0, 2));
        System.out.println("getParent: " + path.getParent());
        System.out.println("getRoot: " + path.getRoot());

        var path1 = Paths.get("C:/Users/../asus/Documents/Downloads").normalize();
        System.out.println(path1);
        //resolve結合路徑
        var pathSrc = Paths.get("C:/Users");
        var pathDest = Paths.get("asus");
        var resolvePath = pathSrc.resolve(pathDest);
        System.out.println(resolvePath);
        //relativize 切換路徑cmd
        var p1 = path;
        var p2 = Paths.get("C:\\Users\\asus\\TestProject\\BasicProject");
        var p1ToP2 = p1.relativize(p2);
        System.out.println(p1ToP2);
    }
}
