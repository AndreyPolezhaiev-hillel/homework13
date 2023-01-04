package com.polezhaiev.homework13.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class FileData {
    private File file;
    private String fileName;
    private long fileLength;
    private String filePath;

    public FileData(String fileName, long fileLength) {
        file = new File(fileName);
        this.filePath = file.getAbsolutePath();

        if(!file.exists()){
            try {
                file.createNewFile();

            } catch (IOException e) {
                throw new RuntimeException(e);

            }
        }

        this.fileName = file.getName();
        this.fileLength = fileLength;
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileLength() {
        return fileLength;
    }

    public String getFilePath() {
        return filePath;
    }


}
