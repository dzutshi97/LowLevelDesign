package coding.Atlassian.EntryExit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//link  - https://leetcode.com/discuss/interview-question/4366572/Atlassian-or-PSE-or-Screening-or-Reject
public class EntryExit {
    HashMap<String,Set<String>> employeeRecords = new HashMap<>();
    HashSet<String> entryWithoutExit = new HashSet<>();
    HashSet<String> exitWithoutEntry = new HashSet<>();



    public Map<String, Set<String>> segregateEmployess(String[][] records){
        Map<String, Set<String>> result=new HashMap<>();
        Set<String> exitWithoutEntry=new HashSet<>();
        Set<String> entryWithoutExit=new HashSet<>();
        Set<String> room=new HashSet<>();

        for(String record[]: records){
            String action=record[1];
            String employeeName=record[0];
            if(action.equalsIgnoreCase("exit")){
                // if employee is the room that means valid entry otherwise it's exit without an entry
                if(!room.remove(employeeName)){
                    exitWithoutEntry.add(employeeName);
                }
            }else if(!room.add(employeeName)){ // it's enter case and room should not have entry of this employee
                entryWithoutExit.add(employeeName);
            }
        }
        entryWithoutExit.addAll(room);// log entry ends
        result.put("enter",entryWithoutExit);
        result.put("exit",exitWithoutEntry);
        return result;
    }

    public static void main(String[] args) {
//        segregateEmployess()

    }
}
/*
* entryWithoutExit.remove()

        for(String[] record : records){
            String emp = record[0];
            String status = record[1];
            if(!employeeRecords.containsKey(emp)){
                HashSet<String> hs = new HashSet<>();
                hs.add(status);
                employeeRecords.put(emp,hs);
            }else{
                if(employeeRecords.get(emp).equals("entry")){
                    if(employeeRecords.get(emp).contains("exit")){
                        employeeRecords.get(emp).remove("exit");
                    }
                }else {
                    if(employeeRecords.get(emp).equals("exit")){
                        if(employeeRecords.get(emp).contains("entry")){
                            employeeRecords.get(emp).remove("entry");
                        }
                    }
                }
            }
        }
        employeeRecords.forEach((k,v) -> v.contains("exit") && !v.contains("entry"));


        return null;*/
