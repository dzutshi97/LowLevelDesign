package com.Uber.VersionManagement;

public class VersionCompatibilityManagement {

    /**
     *
     Walkthrough of the Example Test Case:
     Step 1: Add Version 1 (versions.addNewVersion(1, false))
     Since ver = 1 and isCompatibleWithPrev = false, version 1 starts a new group.
     accumulate[1] = 1.
     Step 2: Add Version 2 (versions.addNewVersion(2, true))
     Version 2 is compatible with version 1.
     accumulate[2] = accumulate[1] = 1, meaning version 2 joins version 1's group.
     Step 3: Add Version 3 (versions.addNewVersion(3, true))
     Version 3 is compatible with version 2.
     accumulate[3] = accumulate[2] = 1, meaning version 3 also joins the group of version 1.
     Step 4: Add Version 4 (versions.addNewVersion(4, false))
     Version 4 is not compatible with version 3, so it starts a new group.
     accumulate[4] = 4, meaning version 4 is its own root.
     Step 5: Add Version 5 (versions.addNewVersion(5, true))
     Version 5 is compatible with version 4.
     accumulate[5] = accumulate[4] = 4, meaning version 5 joins the group of version 4.
     Step 6: Add Version 6 (versions.addNewVersion(6, true))
     Version 6 is compatible with version 5.
     accumulate[6] = accumulate[5] = 4, meaning version 6 also joins the group of version 4.
     Testing isCompatible:
     versions.isCompatible(1, 3)

     accumulate[1] = 1 and accumulate[3] = 1, so both versions are in the same group and are compatible.
     Result: true.
     */

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
