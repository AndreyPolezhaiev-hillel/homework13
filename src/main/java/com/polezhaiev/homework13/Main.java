package com.polezhaiev.homework13;

import com.polezhaiev.homework13.exception.FilePathException;
import com.polezhaiev.homework13.model.FileData;
import com.polezhaiev.homework13.service.FileNavigator;
import com.polezhaiev.homework13.service.FileNavigatorService;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FilePathException {
        FileData file = new FileData("text.txt");
        FileData file1 = new FileData("text1.txt");
        FileData file2 = new FileData("text2.txt");
        FileData file3 = new FileData("text3.txt");
        FileData file4 = new FileData("text4.txt");

        FileNavigatorService fileService = new FileNavigator();

        fileService.add(file, "Files");
        fileService.add(file1, "Files");

        fileService.add(file2, "Files2");
        fileService.add(file3, "Files2");

        fileService.add(file4, "Files1");


        ///////////////   METHODS   /////////////////////////
        System.out.println(fileService.find("Files2"));
        System.out.println(fileService.filterBySize(18));
        fileService.remove("Files");
        System.out.println(fileService.sortBySize());


    }
}
