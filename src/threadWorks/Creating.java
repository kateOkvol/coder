package threadWorks;

import fileWorks.FileCoder;

import java.io.File;

public class Creating extends Thread {
    private boolean isEncode;
    private File newFile;
    private File file;

    public Creating(File file, boolean isEncode) {
        this.isEncode = isEncode;
        this.file = file;
    }

    public File getNewFile() {
        return newFile;
    }

    @Override
    public void run() {
        System.out.println("создать");
        newFile = new FileCoder().createFile(file, isEncode);
    }
}