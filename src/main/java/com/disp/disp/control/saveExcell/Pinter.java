package com.disp.disp.control.saveExcell;

import java.awt.*;

/**
 * Created by disp.chimc on 21.11.14.
 */
public class Pinter {

    private int start;
    private int end;
    private Color color;
    private String place;
    private int min;
    public Pinter( int start, int end, Color color) {

        this.start = start;
        this.end = end;
        this.color = color;
    }

    public Pinter(int start, int end, Color color, String place,int min) {
        this.start = start;
        this.end = end;
        this.color = color;
        this.place = place;
        this.min = min;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "Pinter{" +
                "start=" + start +
                ", end=" + end +
                ", color=" + color +
                ", place='" + place + '\'' +
                ", minute='" + min + '\'' +
                '}';
    }
}
