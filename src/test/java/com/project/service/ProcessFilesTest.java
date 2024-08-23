package com.project.service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessFilesTest {
    /*
    *   file1.txt (size: 100)
        file2.txt (size: 200) in collection "collection1"
        file3.txt (size: 200) in collection "collection1"
        file4.txt (size: 500) in collection "collection2"
        file5.txt (size: 10)
    * */
    @Test
    public  void testProcessFiles(){
        Map<String, List<File>> collectionOfFiles=new HashMap<>();
        List<File> noCollection=new ArrayList<>();
        noCollection.add(new File("file1.txt",100));
        noCollection.add(new File("file5.txt",10));
        File file2=new File("file2.txt",200);
        File file3=new File("file3.txt",200);
        File file4=new File("file4.txt",500);
        File file6=new File("file6.txt",600);
        List<File> collection1Files=new ArrayList<>();
        collection1Files.add(file2);
        collection1Files.add(file3);
        collection1Files.add(file6);
        List<File> collection2Files=new ArrayList<>();
        collection2Files.add(file4);
        List<File> collection3Files=new ArrayList<>();
        collection3Files.add(file6);

        collectionOfFiles.put("collection1",collection1Files);
        collectionOfFiles.put("collection2",collection2Files);
        collectionOfFiles.put("collection3",collection3Files);

        ProcessFiles processFiles=new ProcessFiles(collectionOfFiles,noCollection);
        System.out.println("Size : "+processFiles.getTotalFilesSize());
        System.out.println("Top N: "+processFiles.getTopNCollections(2));
    }

}
