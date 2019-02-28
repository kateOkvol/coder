package threadWorks;

import fileWorks.FileCoder;

import java.io.File;

public class Reading extends Thread {
    private FileCoder fileCoder;

    public Reading(File file, boolean isEncode) {
        fileCoder = new FileCoder(file, isEncode);
    }

    public FileCoder getFileCoder() {
        return fileCoder;
    }

    @Override
    public void run() {
        System.out.println("читать");
        fileCoder.startRead(fileCoder.getFile());
    }
}