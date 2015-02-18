package com.disp.disp.control.loadExcell;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LyDjons on 10.10.14.
 */
public class TransportAction {
    private String status;
    private String place;
    private Date start;
    private Date end;
    private Date interval;
    private double milage;
    private double km_from_the_begin;
    private double middle_speed;
    private double max_speed;

    public TransportAction(String status,
                           String place,
                           String start,
                           String end,
                           String interval,
                           String milage,
                           String km_from_the_begin,
                           String middle_speed,
                           String max_speed) throws ParseException {
        this.status=status;
        this.place = place;

        this.start =new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(start);
        this.end = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(end);
        this.interval =new SimpleDateFormat("HH:mm:ss").parse(interval);
        this.milage = Double.valueOf(milage);
        this.km_from_the_begin = Double.valueOf(km_from_the_begin);
        this.middle_speed = Double.valueOf(middle_speed);
        this.max_speed =Double.valueOf(max_speed);


    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlace() {
        return place;
    }

    public Date getStart() {
        return (Date)start.clone();
    }

    public Date getEnd() {
        return (Date)end.clone();
    }

    public double getMilage() {
        return milage;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setStart(Date start) {
        this.start = (Date)start.clone();
    }

    public void setEnd(Date end) {
        this.end = (Date)end.clone();
    }

    public void setMilage(double milage) {
        this.milage = milage;
    }

    public Date getInterval() {
        return interval;
    }

    public void setInterval(Date interval) {
        this.interval = interval;
    }

    public double getKm_from_the_begin() {
        return km_from_the_begin;
    }

    public void setKm_from_the_begin(double km_from_the_begin) {
        this.km_from_the_begin = km_from_the_begin;
    }

    public double getMiddle_speed() {
        return middle_speed;
    }

    public void setMiddle_speed(double middle_speed) {
        this.middle_speed = middle_speed;
    }

    public double getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(double max_speed) {
        this.max_speed = max_speed;
    }

    @Override
    public String toString() {
        return "TransportAction{" +
                "status='" + status + '\'' +
                ", place='" + place + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", milage=" + milage +
                ", km_from_the_begin=" + km_from_the_begin +
                ", middle_speed=" + middle_speed +
                ", max_speed=" + max_speed +
                '}';
    }
}
