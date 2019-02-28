import fileWorks.FileCoder;

import java.io.File;

public class CoderTest {
    public static void main(String[] args) {
        FileCoder fileCoder = new FileCoder();
        File file = new File("task.txt");

        File encodeFile = fileCoder.createFile(file, true);
        fileCoder.justEncode(file, encodeFile, true);

        File decodeFile = fileCoder.createFile(file, false);
        fileCoder.justEncode(encodeFile, decodeFile, false);
    }
}