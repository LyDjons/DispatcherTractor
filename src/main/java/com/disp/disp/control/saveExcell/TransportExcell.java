package com.disp.disp.control.saveExcell;

import com.config.Config;
import com.disp.disp.control.loadExcell.Report;
import com.disp.disp.control.loadExcell.TransportAction;

import java.awt.*;
import java.util.*;

/**
 * Created by disp.chimc on 31.10.14.
 */
public class TransportExcell {
    private int tracker;
    private String department;
    private String transport_mark;
    private String gos;
    private String type_of_work;
    private String fio;
    private Date start;
    private Date end;
    private ArrayList<Pinter> pintersList;

    public String getDepartment() {
        return department;
    }

    public ArrayList<TransportAction> getTransportAction(Report report){
        return report.getTransportActions();
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTransport_mark() {
        return transport_mark;
    }

    public void setTransport_mark(String transport_mark) {
        this.transport_mark = transport_mark;
    }

    public String getGos() {
        return gos;
    }

    private String getGos(int tracker,ArrayList<Config>configs) {
if(configs==null) return "нет в config";
        String track = String.valueOf(tracker);
        for(Config c: configs){
            if(c.getTracker().contains(track)) {
                return c.getGos();

            }
        }
      return  "-";
    }

    public int getTracker() {
        return tracker;
    }

    public void setTracker(int tracker) {
        this.tracker = tracker;
    }

    public void setGos(String gos) {
        this.gos = gos;
    }

    public String getType_of_work() {
        return type_of_work;
    }

    public void setType_of_work(String type_of_work) {
        this.type_of_work = type_of_work;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getStart() {
        return (Date)start.clone();
    }

    public void setStart(Date start) {
        this.start = (Date)start.clone();
    }

    public Date getEnd() {
        return (Date)end.clone();
    }

    public void setEnd(Date end) {
        this.end = (Date)end.clone();
    }
    private String getDriver(Report report,ArrayList<Config> configs){
        if(configs==null) return "-";
        String tracker = String.valueOf(report.getTracker());
        for(Config c: configs){
            if(c.getTracker().contains(tracker)) return c.getName();
        }
        return "-";
    }
    public TransportExcell(Report report,ArrayList<Config>configs,Map<String,String> departMap) {
        tracker = report.getTracker();
        department= get_list_departments_of_work(report,configs,departMap);
        transport_mark = get_transport_mark(report.getTracker(),report.getTransport(),configs);
        gos =getGos(report.getTracker(), configs);
        type_of_work = get_type_of_work(report.getTracker(),configs);
        fio  = getDriver(report, configs);
        if(getStartWork(report)==null){

            start=(Date)report.getTime_total().clone();
            start.setHours(0);
            start.setMinutes(0);
            start.setSeconds(0);
        }else
        {start = (Date)getStartWork(report).clone();}
        if(getEndWork(report)==null){

            end=(Date)report.getTime_stop().clone();
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);
        }else {
            end = (Date)getEndWork(report).clone();}
        pintersList=getPainterListIntervalNumColumn(report.getTransportActions());
        if(gos.contains(".")) gos = gos .substring(0,gos.indexOf("."));

    }
    public static String get_transport_mark(int tracker,String transpory_marck,ArrayList<Config>configs){
       if(configs==null) return transpory_marck;
    for(Config c :configs){
           if(Integer.valueOf(c.getTracker())==tracker) return c.getMark();
    }
      return transpory_marck;
    }
          //получение начала движения
    private static Date getStartWork(Report report){
        for(TransportAction transportAction :report.getTransportActions()){
            if (transportAction.getStatus().equals("Движение")) return (Date)transportAction.getStart().clone();
        }
        return null;
    }
         //олучение окончания движения
    private static Date getEndWork(Report report){
        ArrayList<TransportAction> ta = report.getTransportActions();
        for(int j=ta.size()-1;j>-1;j--){
            if (ta.get(j).getStatus().equals("Движение")) {
                return (Date)ta.get(j).getEnd().clone();
                 }
        }
       return  null;
    }

        //получение типа культуры с поля
    private static String get_type_of_work(int tracker,ArrayList<Config> configs){
        if(configs==null) return "-";
        String track = String.valueOf(tracker);
        for(Config c: configs){
            if(c.getTracker().contains(track)) return c.getType_work();

        }
        return "-";
    }

    //получение Названия отделения, где находился транспорт
    private static String get_list_departments_of_work(Report report,ArrayList<Config>configs,Map<String,String> departMap){
        String place ="";
        if(configs==null){ return "-";}
        for(Config c : configs){
            if (Integer.parseInt(c.getTracker())==report.getTracker()){
                if(c.getType_work().contains("збирання")) {
                    place+="Комбайни ";
                }else
                if(c.getType_work().contains("бункер")) {
                    place+="Бункера  ";
                }
            }
        }

        Set<String> places = new HashSet<String>();

        for(TransportAction ta: report.getTransportActions()){
                for(Map.Entry<String,String> m: departMap.entrySet()){
                    if(ta.getPlace().contains(m.getKey())){

                        try {
                            int i = Integer.parseInt(ta.getPlace().substring(0,1));
                        }catch (Exception e){
                            continue;
                        }

                        places.add(m.getValue());
                    }
                }
        }

        for(String s : places){
            place = place+" "+s+",";
        }
        if(place.endsWith(","))
            place =place.substring(0,place.length()-1);

        return place;

    }
    public ArrayList<Pinter> getPainterListIntervalNumColumn(ArrayList<TransportAction> action){
        ArrayList<Pinter> painterarray = new ArrayList<Pinter>();

        for(TransportAction transportAction:action){

            if(transportAction.getStart().getHours()<7) continue;
if(transportAction.getStatus().contains("Стоянка") && transportAction.getInterval().getMinutes()<15 &&
transportAction.getInterval().getHours()<0) continue;

            int start =get_num_cell(transportAction.getStart());
            int end = get_num_cell(transportAction.getEnd());
            if(start==end) continue;
            if(transportAction.getStatus().contains("Стоянка") && (transportAction.getInterval().getHours()>0 ||
            transportAction.getInterval().getMinutes()>14)){
                painterarray.add(new Pinter(start,end,new Color(255,255,0)));
            }
            if(transportAction.getMiddle_speed()>14 && transportAction.getStatus().contains("Движение")){
                painterarray.add(new Pinter(start,end,new Color(0,176,240)));
            }
            if(transportAction.getStatus().contains("Движение") && transportAction.getMiddle_speed()<14){
                painterarray.add(new Pinter(start,end,new Color(0,176,80)));
            }
        }

        //deleted start yellow
        for(Pinter p : painterarray){
            if(p.getColor().equals(new Color(255,255,0)))
                p.setColor(new Color(255,255,255));
            else
            if(p.getColor().equals(new Color(0,176,80))){
                break;
            }
        }
        //deleted end Yellow
        for(int i = painterarray.size()-1; i >-1;i--){
            if(painterarray.get(i).getColor().equals(new Color(255,255,0))){
                painterarray.get(i).setColor(new Color(255,255,255));
            }
            if(painterarray.get(i).getColor().equals(new Color(0,176,80))){
                break;
            }
        }
        return painterarray;
    }

    public int getFirstIndexWorkGreen(ArrayList<Pinter> list){
        for(Pinter p : list){
            if(p.getColor().equals(new Color(0,176,80))) return p.getStart();
        }
        return 5;
    }

    public int getLastIndexWorkGreen(ArrayList<Pinter> list){
        int index =5;
        for(Pinter p : list){
            if(p.getColor().equals(new Color(0,176,80))) index=p.getEnd();
        }
        return index;
    }

    //округление даты (времени) до 15 мин
    private static Date data_rounding(Date date){
        int switchVariable = 0;
        double minute = date.getMinutes();
        if(date.getSeconds()>=30) minute+=1;
        if( minute>=0 && minute <7.5) switchVariable = 5;
        else if( minute>=7.5 && minute <=15) switchVariable = 10;
        else if( minute>15 && minute <22.5) switchVariable = 20;
        else if( minute>=22.5 && minute <=30) switchVariable = 25;
        else if( minute>30 && minute <37.5) switchVariable = 35;
        else if( minute>=37.5 && minute <=45) switchVariable = 40;
        else if( minute>45 && minute <52.5) switchVariable = 50;
        else if( minute>52.5 && minute <60) switchVariable = 55;
        switch (switchVariable)
        {
            case 5: date.setMinutes(0);date.setSeconds(0); break;
            case 10: date.setMinutes(15);date.setSeconds(0); break;
            case 20: date.setMinutes(15);date.setSeconds(0); break;
            case 25: date.setMinutes(30);date.setSeconds(0); break;
            case 35: date.setMinutes(30);date.setSeconds(0); break;
            case 40: date.setMinutes(45);date.setSeconds(0); break;
            case 50: date.setMinutes(45);date.setSeconds(0); break;
            case 55: date.setHours(date.getHours()+1);date.setMinutes(0);date.setSeconds(0); break;
            default: date.setMinutes(0);date.setSeconds(0); break;
        }
        return (Date)date.clone();
    }

    private static int  get_num_cell(Date date){
        date = (Date)data_rounding(date).clone();
        Date countdata = new Date();
        countdata.setHours(7);
        countdata.setMinutes(0);
        countdata.setSeconds(0);
        int cell=5;
        for(int i =0;i<101;i++){
            if(date.getHours()==countdata.getHours()&& date.getMinutes()==countdata.getMinutes()) {
                return cell;
            } countdata.setMinutes(countdata.getMinutes()+15);
            cell++;
        }
        return -1;
    }

    public ArrayList<Pinter> getPintersList() {
        return pintersList;
    }

    @Override
    public String toString() {
        return "TransportExcell{" +
                "department='" + department + '\'' +
                ", transport_mark='" + transport_mark + '\'' +
                ", gos='" + gos + '\'' +
                ", type_of_work='" + type_of_work + '\'' +
                ", fio='" + fio + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

}
