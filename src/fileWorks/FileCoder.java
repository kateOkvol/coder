package fileWorks;

import threadWorks.Creating;
import threadWorks.Encoding;
import threadWorks.Reading;
import threadWorks.Writing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCoder {
    private File file;
    private boolean isEncode;
    private StringBuilder build = new StringBuilder();

    private static final Integer KEY = 17;
    private static final Integer INDEX = 18;

    private static final String DOT = ".";
    private static final String UNDERSCORE = "_";


    public FileCoder() {
    }

    public FileCoder(File file, boolean isEncode) {
        this.file = file;
        this.isEncode = isEncode;
    }

    public boolean isEncode() {
        return isEncode;
    }

    public File getFile() {
        return file;
    }

    synchronized public File createFile(File file, boolean isEncode) {
        StringBuilder builder = new StringBuilder(file.getPath());
        if (isEncode) {
            builder.insert(builder.lastIndexOf(DOT), UNDERSCORE + CodeFlag.ENCODED.name().toLowerCase());
        } else {
            builder.insert(builder.lastIndexOf(DOT), UNDERSCORE + CodeFlag.DECODED.name().toLowerCase());
        }
        return new File(builder.toString());
    }

    synchronized public void startRead(File file) {
        String text;
        try (BufferedReader reader = new BufferedReader(new FileReader(file.getPath()))) {
            while ((text = reader.readLine()) != null) {
                for (int i = 0; i < text.length(); i++) {
                    build.append(text.charAt(i));
                }
            }
        } catch (IOException e) {
            System.out.println("I/O error.");
        }
    }

    synchronized public void startWrite(File newFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile.getPath()))) {
            writer.write(build.toString());
        } catch (IOException e) {
            System.out.println("I/O error.");
        }
    }

    synchronized public void encode(boolean isEncode) {
        if (!isEncode) {
            foundKey(build);
        }
        for (int i = 0; i < build.length(); i++) {
            build.replace(i, i + 1, String.valueOf(code(build.charAt(i))));
        }
        if (isEncode) {
            hideKey(build);
        }
    }

    private char code(char ch) {
        return (char) (ch ^ KEY);
    }

    private void hideKey(StringBuilder builder) {
        builder.insert((int) INDEX, KEY);
    }

    private void foundKey(StringBuilder builder) {
        builder.delete(INDEX, (KEY.toString().length() + INDEX));
    }

    public void justEncode(File file, File newFile, boolean isEncode) {
        startRead(file);
        encode(isEncode);
        startWrite(newFile);
    }

    public File multiThreadEncode(File file, boolean isEncode) {
        System.out.println("begin");
        Creating creating = new Creating(file, isEncode);
        Reading reading = new Reading(file, isEncode);
        creating.run();
        reading.run();
        try {
            creating.join();
            reading.join();
        } catch (InterruptedException e) {
            System.out.println("Creating or reading error");
        }
        Encoding encoding = new Encoding(reading.getFileCoder());
        encoding.start();
        try {
            encoding.join();
        } catch (InterruptedException e) {
            System.out.println("Encoding error");
        }
        Writing writing = new Writing(encoding.getFileCoder(), creating.getNewFile());
        writing.start();
        try {
            writing.join();
        } catch (InterruptedException e) {
            System.out.println("Writing error");
        }
        System.out.println("end");
        return writing.getNewFile();
    }
}