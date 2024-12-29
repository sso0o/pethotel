package com.example.pethotel.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FileUtil {
    public static String encodeFilePath(String filePath) throws UnsupportedEncodingException {
        return URLEncoder.encode(filePath, "UTF-8").replaceAll("\\+", "%20"); // 공백은 %20으로 변경
    }
}
