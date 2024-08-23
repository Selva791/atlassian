package com.project.service;

import java.util.*;

public class ProcessFiles implements FilesOperation{
    Map<String,List<File>> collectionOfFiles;
    List<File> noCollection;
    ProcessFiles(Map<String, List<File>> collectionOfFiles, List<File> noCollection){
        this.collectionOfFiles=collectionOfFiles;
        this.noCollection=noCollection;

    }
    @Override
    public int getTotalFilesSize() {
      //  recursion(collectionOfFiles);
        ///collectionOfFiles.size()*list files + O(noCollection.size())
        int totalSize=0;
        Set<File> filesSet=new HashSet<>();
        for(String key: collectionOfFiles.keySet()){
            List<File> listOfFiles=collectionOfFiles.get(key);
            for(File file:listOfFiles){
                if(!filesSet.contains(file)){
                    totalSize+=file.getFileSize();
                    filesSet.add(file);
                }

            }

        }
        for(File file:noCollection){
            if(!filesSet.contains(file)){
                totalSize+=file.getFileSize();
                filesSet.add(file);
            }

        }
        return totalSize;
    }

  /*  private void recursion( List<String> collections) {

        for(String key:collections){
            List<String> nested=collectionOfFiles.get(key);
            recursion(nested);
        }
    }*/

    @Override
    public List<String> getTopNCollections(int topCollections) {
        Queue<Integer> q=new LinkedList<>();
        Deque<Integer> qd=new ArrayDeque<>();
        Map<Integer,Deque<Integer>> mp=new HashMap<>();
        mp.computeIfAbsent(1,c->new ArrayDeque<>()).add(1);
        //collectionOfFiles.size()*list files
        List<String> topNCollections=new ArrayList<>();
        PriorityQueue<Map.Entry<String,Integer>> fileCollectionSizeHolder=new PriorityQueue<>((a,b)->b.getValue()-a.getValue());
        Map<String,Integer> collectionSizeMap=new HashMap<>();
        for(String key:collectionOfFiles.keySet()){
            int collectionSize=0;
            List<File> files=collectionOfFiles.get(key);
            for(File file:files){
                collectionSize+=file.getFileSize();
            }
            collectionSizeMap.put(key,collectionSize);
        }
        fileCollectionSizeHolder.addAll(collectionSizeMap.entrySet());
        //nlog n
        while(!fileCollectionSizeHolder.isEmpty()&&topCollections>0){
            topNCollections.add(fileCollectionSizeHolder.poll().getKey());
            topCollections--;
        }
        return topNCollections;
    }
}
