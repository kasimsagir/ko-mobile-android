package com.kinght.commerce.data.network.entities.Event;

import com.kinght.commerce.utility.Constant;

import java.util.Calendar;

public class Hour {
    private String hour;
    private boolean isSelected=false;
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public long getCurrentMilisTime(String day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, returnDayFromString(day));
        calendar.set(Calendar.HOUR_OF_DAY, returnHourOfString(hour));
        calendar.set(Calendar.MINUTE, returnMinuteOfString(hour)- Constant.ALARM_INCREASE);

        return calendar.getTimeInMillis();

    }

    public long getCurrentMilisTime(int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, day);
        calendar.set(Calendar.HOUR_OF_DAY, returnHourOfString(hour));
        calendar.set(Calendar.MINUTE, returnMinuteOfString(hour)- Constant.ALARM_INCREASE);

        return calendar.getTimeInMillis();

    }
    public Integer returnDayFromString(String day) {
        switch (day) {
            case "Monday":
                return Calendar.MONDAY;
            case "Sunday":
                return Calendar.SUNDAY;
            case "Tuesday":
                return Calendar.TUESDAY;
            case "Wednesday":
                return Calendar.WEDNESDAY;
            case "Thursday":
                return Calendar.THURSDAY;
            case "Friday":
                return Calendar.FRIDAY;
            case "Saturday":
                return Calendar.SATURDAY;
            default:
                return null;
        }
    }
    public Integer returnHourOfString(String hour) {
        if(!isExactTime(hour)){
            if(Integer.parseInt(hour.split(":")[0]) == 0){
                return 23;
            }else {
                return Integer.parseInt(hour.split(":")[0])-1;
            }

        }else {
            return Integer.parseInt(hour.split(":")[0]);
        }
    }

    public Integer returnMinuteOfString(String hour) {
        if(!isExactTime(hour)){
            return 60-Constant.ALARM_INCREASE;
        }else {
            return Integer.parseInt(hour.split(":")[1]);

        }
    }

    public boolean isExactTime(String hour){
        if(Integer.parseInt(hour.split(":")[1])-Constant.ALARM_INCREASE <0){
            return false;
        }else {
            return true;
        }
    }
}
