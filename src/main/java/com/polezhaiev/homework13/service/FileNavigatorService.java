package com.polezhaiev.homework13.service;

import com.polezhaiev.homework13.exception.FileAlreadyExistsException;
import com.polezhaiev.homework13.exception.FilePathException;
import com.polezhaiev.homework13.model.FileData;

import java.io.File;
import java.util.List;

public interface FileNavigatorService {
    boolean add(String path) throws FilePathException, FileAlreadyExistsException;
    List<File> find(String path);
    List<File> filterBySize(long size);
    void remove(String path);
    List<File> sortBySize();
}
