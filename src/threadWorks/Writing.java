package threadWorks;

import fileWorks.FileCoder;

import java.io.File;

public class Writing extends Thread {
    private FileCoder fileCoder;
    private File newFile;

    public Writing(FileCoder fileCoder, File newFile) {
        this.fileCoder = fileCoder;
        this.newFile = newFile;
    }

    public File getNewFile() {
        return newFile;
    }

    @Override
    public void run() {
        System.out.println("записать");
        fileCoder.startWrite(newFile);
    }
}