package com.polezhaiev.homework13.service;

import com.polezhaiev.homework13.exception.FileAlreadyExistsException;
import com.polezhaiev.homework13.exception.FilePathException;
import com.polezhaiev.homework13.model.FileData;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileNavigator implements FileNavigatorService{
    Map<Path, List<File>> files = new HashMap();


    @Override
    public boolean add(String path) throws FilePathException, FileAlreadyExistsException {

        Path filePath = Paths.get(path);
        Path fileName = filePath.getFileName();
        Path parent = filePath.getParent();
        File resultFile = new File(fileName.toString());

        try{
            resultFile.exists();

            File resultFile1 = new File(path);
            Path filePath1 = Paths.get(resultFile1.getAbsolutePath());
            Path parent1 = filePath1.getParent();

            if(path.equals(resultFile.getPath())){
                if(files.containsKey(parent1)){
                    files.get(parent1).add(resultFile1);

                } else {
                    List resultList = new ArrayList<>();
                    resultList.add(resultFile1);


                    files.put(parent1, resultList);
                    return true;
                }
            }

            if(files.containsKey(parent)){
                files.get(parent).add(resultFile);

            } else {
                List resultList = new ArrayList<>();
                resultList.add(resultFile);

                files.put(parent, resultList);
                return true;
            }

        } catch (RuntimeException e){
            throw new FileAlreadyExistsException("File " + resultFile + " already exists");

        }
        return true;
    }

    @Override
    public List<File> find(String path) {
        Path filePath = Paths.get(path);

        if(files.containsKey(filePath)){
            return files.get(filePath);

        }
        return null;
    }

    @Override
    public List<File> filterBySize(long size) {
        if(size < 0){
            System.out.println("Size less than 0");
            return null;

        }
        List <List<File>> mainList = new ArrayList<>();
        List <File> resultList = new ArrayList<>();

        for(Map.Entry<Path, List<File>> entry: files.entrySet()){
            mainList.add(entry.getValue());

        }

        for (int i = 0; i < mainList.size(); i++) {
            for (int j = 0; j < mainList.get(i).size(); j++) {
                if(mainList.get(i).get(j).length() <= size){
                    resultList.add(mainList.get(i).get(j));

                }
            }
        }

        return resultList;
    }

    @Override
    public void remove(String path) {
        Path filePath = Paths.get(path);

        if(files.containsKey(filePath)){
            files.remove(filePath);
            System.out.println("Path \"" + filePath + "\" successfully removed!");
        } else{
            System.out.println("No matching key!");
        }

    }

    @Override
    public List<File> sortBySize() {
        if(files.size() == 0 || files == null){
            System.out.println("Nothing to sort, no contained elements");
            return null;
        }

        int size = 0;
        for(Map.Entry<Path, List<File>> entry: files.entrySet()){
            if(files.containsKey(entry.getKey())){
                for (int i = 0; i < entry.getValue().size(); i++) {
                    size++;
                }
            }
        }


        File [] fileArr = new File[size];
        List<File> resultList = new ArrayList<>();

        int index = 0;
        for(Map.Entry<Path, List<File>> entry: files.entrySet()){
            if(files.containsKey(entry.getKey())){
                for (int i = 0; i < entry.getValue().size(); i++) {
                    if(index == entry.getValue().size()){
                        fileArr[index] = entry.getValue().get(i);
                    }
                    fileArr[index++] = entry.getValue().get(i);
                }
            }
        }

        for (int i = 0; i < fileArr.length; i++) {
            for (int j = 0; j < fileArr.length - 1; j++) {
                if(fileArr[j].length() > fileArr[j + 1].length()){
                    File file = fileArr[j + 1];
                    fileArr[j + 1] = fileArr[j];
                    fileArr[j] = file;
                }
            }
        }

        for (int i = 0; i < fileArr.length; i++) {
            resultList.add(fileArr[i]);
        }

        return resultList;
    }


}
