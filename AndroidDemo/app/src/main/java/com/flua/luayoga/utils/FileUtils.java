package com.flua.luayoga.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xianjiachao on 2019/1/16.
 */
public class FileUtils {
    public static void StringToFile(String stringFile, File toFile) {
        try {
            if (toFile.exists()) {
                toFile.delete();
            }
            toFile.mkdirs();
            toFile.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(toFile);
            byte[] strToBytes = stringFile.getBytes();
            outputStream.write(strToBytes);
            outputStream.close();
        } catch (IOException e) {
            // ignore
        }
    }
}
