package threadWorks;

import fileWorks.FileCoder;

import java.io.File;

public class Manager {
    public static void main(String[] args) {
        FileCoder fileCoder = new FileCoder();

        String path = "task_thread.txt";
        File file = new File(path);

        File encodeFile = fileCoder.multiThreadEncode(file, true);
        fileCoder.multiThreadEncode(encodeFile, false);
    }
}