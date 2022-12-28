package com.polezhaiev.homework13.service;

import com.polezhaiev.homework13.exception.FilePathException;
import com.polezhaiev.homework13.model.FileData;

import java.io.File;
import java.util.*;

public class FileNavigator implements FileNavigatorService{
    Map<String, List<File>> files = new HashMap();


    @Override
    public void add(FileData file, String path) throws FilePathException {

        if(!file.getFilePath().equals(path)){
            throw new FilePathException("Key path " + path + " doesn't equal file path " + file.getFilePath());
        }

        if(files.containsKey(path)){
            files.get(path).add(file.getFile());

        } else {
            List <File> fileDataList = new ArrayList<>();
            fileDataList.add(file.getFile());

            files.put(path, fileDataList);
        }
    }

    @Override
    public List<File> find(String path) {
        if(files.containsKey(path)){
            return files.get(path);

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

        for(Map.Entry<String, List<File>> entry: files.entrySet()){
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
        if(files.containsKey(path)){
            files.remove(path);
            System.out.println("Path \"" + path + "\" successfully removed!");
        } else{
            System.out.println("No matching key!");
        }

    }

    @Override
    public List<File> sortBySize() {
        if(files.size() == 0 || files == null){
            return null;
        }

        int size = 0;
        for(Map.Entry<String, List<File>> entry: files.entrySet()){
            if(files.containsKey(entry.getKey())){
                for (int i = 0; i < entry.getValue().size(); i++) {
                    size++;
                }
            }
        }


        File [] fileArr = new File[size];
        List<File> resultList = new ArrayList<>();

        int index = 0;
        for(Map.Entry<String, List<File>> entry: files.entrySet()){
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
