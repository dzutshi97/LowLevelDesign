package com.Uber.VersionManagement;

public class VersionCompatibilityManagement {

    //disjoint set parent property
    int[] accumulate;
    public void addNewVersion(int ver, boolean isCompatibleWithPrev)
    {
        if(isCompatibleWithPrev){
            accumulate[ver] = accumulate[ver-1];
        }else{
            accumulate[ver] = ver;
        }
    }

    public boolean isCompatible(int srcVer, int targetVer)
    {
        if(accumulate[targetVer] == accumulate[srcVer]){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        versions.addNewVersion(1, false);
//        versions.addNewVersion(2, true);
//        versions.addNewVersion(3, true);
//        versions.addNewVersion(4, false);
//        versions.addNewVersion(5, true);
//        versions.addNewVersion(6, true);

    }
}
//https://leetcode.com/discuss/interview-question/1657152/uber-software-engineer-ii-l4-onsite-interview-nov-2021
