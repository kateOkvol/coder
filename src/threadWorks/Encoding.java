package threadWorks;

import fileWorks.FileCoder;

public class Encoding extends Thread {
    private FileCoder fileCoder;

    public Encoding(FileCoder fileCoder) {
        this.fileCoder = fileCoder;
    }

    public FileCoder getFileCoder() {
        return fileCoder;
    }

    @Override
    public void run() {
        System.out.println("закодировать");
        fileCoder.encode(fileCoder.isEncode());
    }
}
