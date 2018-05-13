package com.coddington.poom.util;

public class ScheduleUtil {

	public static String getDayOfWeek(int value) {		
		    String day = "";
		    switch(value){
		    case 1:
		        day="sun";
		        break;
		    case 2:
		        day="mon";
		        break;
		    case 3:
		        day="tue";
		        break;
		    case 4:
		        day="wed";
		        break;
		    case 5:
		        day="thu";
		        break;
		    case 6:
		        day="fri";
		        break;
		    case 7:
		        day="sat";
		        break;
		    }
		    return day;
	}
}
