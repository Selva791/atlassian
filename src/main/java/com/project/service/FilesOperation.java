package com.project.service;

import java.util.List;

public interface FilesOperation {
    public int getTotalFilesSize();
    public List<String> getTopNCollections(int topCollections);
}
