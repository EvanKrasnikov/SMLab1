package ru.geographer29.polytech.labs.storage;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private static String dir = System.getProperty("user.dir");
    private FileWriter writer;

    public void createFile(String fileName){
        if (writer == null) {
            try {
                writer = new FileWriter(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(String data) {
        if (data == null)
            throw new RuntimeException("Data is null");

        try {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (writer != null){
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
