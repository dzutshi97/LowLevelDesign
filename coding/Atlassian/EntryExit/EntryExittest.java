package coding.Atlassian.EntryExit;

import org.junit.Test;

public class EntryExittest {

    EntryExit e = new EntryExit();

    @Test
    public void testSegregateEmployees(){
        String[][] records1 = {
                {"Paul", "enter"}, //no issue
                {"Pauline", "exit"}, // issue, means she entered the room w/o using badge, exit w/o enter
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Martha", "exit"},
                {"Joe", "enter"},
                {"Martha", "enter"},
                {"Steve", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "enter"},
                {"Joe", "enter"},
                {"Curtis", "exit"},
                {"Curtis", "enter"},
                {"Joe", "exit"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "exit"},
                {"Joe", "enter"},
                {"Joe", "enter"},
                {"Martha", "exit"},
                {"Joe", "exit"},
                {"Joe", "exit"},
        };
        e.segregateEmployess(records1);

    }
}
