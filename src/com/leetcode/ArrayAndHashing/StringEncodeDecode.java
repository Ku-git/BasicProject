package com.leetcode.ArrayAndHashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringEncodeDecode {

    public static void main(String[] args) {

        StringEncodeDecode method = new StringEncodeDecode();
        List<String> input = List.of("neet", "code", "love", "you");
        String encodeStr = method.encode(input);
        List<String> decodeStr = method.decode(encodeStr);
        System.out.println(encodeStr);
        System.out.println(decodeStr);
    }

    // 編碼：將字串列表轉換為單一字串
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append(':').append(str); // 使用長度標記和 ":" 分隔
        }
        return sb.toString();
    }

    // 解碼：將單一字串還原為字串列表
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int delimiter = s.indexOf(':', i); // 找到 ":" 分隔符
            int length = Integer.parseInt(s.substring(i, delimiter)); // 提取字串長度
            i = delimiter + 1; // 跳過分隔符 ":"
            result.add(s.substring(i, i + length)); // 根據長度擷取字串
            i += length; // 移動到下一個字串的起始位置
        }
        return result;
    }
}
