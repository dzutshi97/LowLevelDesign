package com.Uber.FilePaths.dao;

import com.Uber.FilePaths.models.Folder;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class FolderDao {

     ConcurrentHashMap<Integer, Integer> childToParentMapping;
     ConcurrentHashMap<Integer, Folder> folderIdToFolderMapping;

    public FolderDao() {
        this.childToParentMapping = new ConcurrentHashMap<>();
        this.folderIdToFolderMapping = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<Integer, Integer> getChildToParentMapping() {
        return childToParentMapping;
    }

    public void setChildToParentMapping(ConcurrentHashMap<Integer, Integer> childToParentMapping) {
        this.childToParentMapping = childToParentMapping;
    }

    public ConcurrentHashMap<Integer, Folder> getFolderIdToFolderMapping() {
        return folderIdToFolderMapping;
    }

    public void setFolderIdToFolderMapping(ConcurrentHashMap<Integer, Folder> folderIdToFolderMapping) {
        this.folderIdToFolderMapping = folderIdToFolderMapping;
    }
}
