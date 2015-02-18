package com.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by disp.chimc on 23.10.14.
 */
@XmlRootElement(name = "transport")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transport {
    private int tracker;
    private String gos;
    private String driver;
    private String phone;
    private double lenght_harvasters;

    public int getTracker() {
        return tracker;
    }

    public void setTracker(int tracker) {
        this.tracker = tracker;
    }

    public String getGos() {
        return gos;
    }

    public void setGos(String gos) {
        this.gos = gos;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLenght_harvasters() {
        return lenght_harvasters;
    }

    public void setLenght_harvasters(double lenght_harvasters) {
        this.lenght_harvasters = lenght_harvasters;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "tracker=" + tracker +
                ", gos='" + gos + '\'' +
                ", driver='" + driver + '\'' +
                ", phone='" + phone + '\'' +
                ", lenght_harvasters=" + lenght_harvasters +
                '}';
    }
}
