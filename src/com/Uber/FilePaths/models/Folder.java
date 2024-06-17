package com.Uber.FilePaths.models;

import java.util.HashMap;
import java.util.List;

public class Folder {
    int id;
    List<Integer> folders;
    String name;

    public Folder(int id, List<Integer> folders, String name) {
        this.id = id;
        this.folders = folders;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getFolders() {
        return folders;
    }

    public void setFolders(List<Integer> folders) {
        this.folders = folders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
