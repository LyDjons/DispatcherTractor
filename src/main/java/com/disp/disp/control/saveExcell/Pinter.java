package com.disp.disp.control.saveExcell;

import java.awt.*;

/**
 * Created by disp.chimc on 21.11.14.
 */
public class Pinter {

    private int start;
    private int end;
    private Color color;

    public Pinter( int start, int end, Color color) {

        this.start = start;
        this.end = end;
        this.color = color;
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

    @Override
    public String toString() {
        return "Pinter{" +

                ", start=" + start +
                ", end=" + end +
                ", color=" + color +
                '}';
    }
}
