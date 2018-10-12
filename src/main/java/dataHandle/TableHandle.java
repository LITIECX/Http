package dataHandle;

import java.util.ArrayList;
import java.util.List;

public class TableHandle {

    private long id;
    private String studentId;
    private String name;
    private String course;
    private String teacher;
    private int starWeek;
    private int endWeek;
    private int oneTow;  //单双周 1为单周，2为双周
    private String classRoom;
    //课程定位坐标 X:week ;Y:section ,sectionSpan
    private int week; //周几
    private int section; //从第几节课开始
    private int sectionSpan; //跨几节课


    public void handle(String user,List<TableData> tableData){

        for (TableData e:tableData) {


        }

    }



    public static void main(String[] args) throws Exception {


    }
}
