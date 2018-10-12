package dataHandle;


import java.util.ArrayList;
import java.util.List;

public class StringUnit {

    public Location handle(String data) {
        Location location = new Location();
        int num = 0;
        int star = data.indexOf("D");
        int end = data.indexOf("_");
        for (int i = star + 1; i < end; i++) {
            num = (int) (data.charAt(i) - 48) + num * 10;
        }
        if (num >= 0 && num < 11) {
            location.setX(1);
            location.setY(num + 1);
        }
        if (num >= 11 && num < 22) {
            location.setX(2);
            location.setY(num - 10);
        }
        if (num >= 22 && num < 33) {
            location.setX(3);
            location.setY(num - 21);
        }
        if (num >= 33 && num < 44) {
            location.setX(4);
            location.setY(num - 32);
        }
        if (num >= 44 && num < 55) {
            location.setX(5);
            location.setY(num - 43);
        }
        if (num >= 55 && num < 66) {
            location.setX(6);
            location.setY(num - 54);
        }
        if (num >= 66) {
            location.setX(7);
            location.setY(num - 65);
        }
        return location;
    }

    public Week weekHandle(String data) {
        Week week = new Week();
        int star = data.indexOf("-");
        int end = data.indexOf(",");
        int starweek = 0;
        int num = 0;
        int num0 = 0;
        if ((int) data.charAt(0) > 100) {
            num0++;
            week.setOneTow(2);
        } else {
            week.setOneTow(1);
        }

        for (int i = num0; i < star; i++) {
            num = (int) (data.charAt(i) - 48) + num * 10;
        }
        starweek = num;
        num = 0;
        for (int i = star + 1; i < end; i++) {
            num = (int) (data.charAt(i) - 48) + num * 10;
        }
        week.setStarWeek(starweek);
        week.setEndWeek(num);
        return week;
    }

    public static List<String> StringHandle(String data){
        List<String> list = new ArrayList<String>();
        int num = 0;
        int star = 0;
        int end = 0;
        int num1= 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '(') {
                num++;
                star = i+1;
                if (num ==1||num ==4) {
                    String e = data.substring(num1,i);
                    list.add(e.trim());
                }
            }
            if(data.charAt(i) == ')'){
                end++;
                list.add(data.substring(star,i));
                if (end == 3 && i < data.length()-1) {
                    num1 = i+1;
                }
            }
        }
        return list;
    }


}
