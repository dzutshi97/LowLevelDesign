package com.Uber.FilePaths;

import com.Uber.FilePaths.dao.FolderDao;
import com.Uber.FilePaths.models.Folder;
import com.Uber.FilePaths.service.FolderService;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Folder f3 = new Folder(3,new ArrayList<>(),"pqr"); //child

        Folder f9 = new Folder(9, new ArrayList<>(), "lmn");

        ArrayList<Integer> al7 = new ArrayList<>();
        al7.add(9);
        Folder f7 = new Folder(7,al7,"ijk");

        ArrayList<Integer> alRoot = new ArrayList<>();
        alRoot.add(3);
        alRoot.add(7);
        Folder root = new Folder(0,alRoot,"root"); //root
        // 0 -> [3 -> [],7 -> [9, -> []]]

        FolderService service = new FolderService();
        service.addFolder(f3);
        service.addFolder(f9);
        service.addFolder(f7);
        service.addFolder(root);


        System.out.println("printing the path for 9!!");
        service.printPath(9);
        service.printPath(0);// fix this
    }
}
