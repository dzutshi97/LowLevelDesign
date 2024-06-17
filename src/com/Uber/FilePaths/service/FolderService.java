package com.Uber.FilePaths.service;
import com.Uber.FilePaths.dao.FolderDao;
import com.Uber.FilePaths.models.Folder;

import java.util.List;

public class FolderService {
    FolderDao folderDao;

    public FolderService() {
        this.folderDao = new FolderDao();
    }

    public void addFolder(Folder folder){
        this.folderDao.getFolderIdToFolderMapping().put(folder.getId(),folder);
        for(Integer subFolderId : folder.getFolders()){
            this.folderDao.getChildToParentMapping().put(subFolderId, folder.getId()); //child id -> parent id
        }
    }

    public void printPath(Integer folderId){
        if(!this.folderDao.getChildToParentMapping().containsKey(folderId) && this.folderDao.getFolderIdToFolderMapping().containsKey(folderId)){ //root folder case
            String rootFileName = this.folderDao.getFolderIdToFolderMapping().get(folderId).getName();
            System.out.println("/"+rootFileName);
            return;
        }
        StringBuilder sb = new StringBuilder(); //print path of single parent - todo
        getPath(folderId, sb);
        sb.reverse().toString();
    }

    public void getPath(Integer folderId, StringBuilder sb){
//        if(!this.folderDao.getChildToParentMapping().containsKey(folderId) || folderId == null){
//            return;
//        }
//        if(folderId == null){
//            return;
//        }
        Folder f = this.folderDao.getFolderIdToFolderMapping().get(folderId);
        sb.append("/").append(f.getName());
        Integer parentId = this.folderDao.getChildToParentMapping().get(folderId);
        if(parentId == null){
            return;
        }
        getPath(parentId, sb); //append parent path
    }
}
