package com.polezhaiev.homework13;

import com.polezhaiev.homework13.exception.FileAlreadyExistsException;
import com.polezhaiev.homework13.exception.FilePathException;
import com.polezhaiev.homework13.model.FileData;
import com.polezhaiev.homework13.service.FileNavigator;
import com.polezhaiev.homework13.service.FileNavigatorService;

public class Main {
    public static void main(String[] args) throws FilePathException, FileAlreadyExistsException {
        FileData file = new FileData("text.txt", 150);
        FileData file1 = new FileData("text1.txt", 150);
        FileData file2 = new FileData("text2.txt", 150);
        FileData file3 = new FileData("text3.txt", 150);
        FileData file4 = new FileData("text4.txt", 150);

        FileNavigatorService fileService = new FileNavigator();

        fileService.add("text.txt");
        fileService.add(file1.getFilePath());

        fileService.add(file2.getFilePath());
        fileService.add(file3.getFilePath());

        fileService.add(file4.getFilePath());

        ///////////////   METHODS   /////////////////////////
        System.out.println(fileService.find("C:\\Users\\Andrey Polezhaiev\\IdeaProjects\\homewrok13"));
        System.out.println("///////////////////");
        System.out.println(fileService.filterBySize(20));
        System.out.println("///////////////////");
        fileService.remove("C:\\Users\\Andrey Polezhaiev\\IdeaProjects\\homewrok13");
        System.out.println("///////////////////");
        System.out.println(fileService.sortBySize());


    }
}
