package com.disp.disp.control.loadExcell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by disp.chimc on 20.10.14.
 */
public class ExcellLoader {
    private  ArrayList<Report> reports = new ArrayList<Report>();
    private Date date;

    //мтод конвертирует информацию о транспорте в объект Report
    private  Report convertStringtoTransport(String str, String info){
        str= str.substring(23,str.length()-9);
        int tracker = Integer.parseInt(str.substring(0, 3));
        String transport =str.substring(5, str.length() - 2) ;
        Double km =Double.parseDouble(info.substring(23,info.indexOf("км")).replace(',','.'));


        String work = info.substring(info.lastIndexOf("Время в пути: ")+14,info.lastIndexOf("\n"));
        String stop = info.substring(info.indexOf("Время стоянок: ")+15,info.length());
        Date time_work = new Date();
        Date time_stop = new Date();

            time_work.setHours(Integer.parseInt(work.substring(0, 2)));
            time_work.setMinutes(Integer.parseInt(work.substring(3, 5)));
            time_work.setSeconds(Integer.parseInt(work.substring(6,8)));
            time_work.setDate(date.getDate());
            time_work.setMonth(date.getMonth());
            time_work.setYear(date.getYear());
try{
            time_stop.setHours(Integer.parseInt(stop.substring(0, 2)));
            time_stop.setMinutes(Integer.parseInt(stop.substring(3, 5)));
            time_stop.setSeconds(Integer.parseInt(stop.substring(6, 8)));
            time_stop.setYear(date.getYear());
            time_stop.setDate(date.getDate());
            time_stop.setMonth(date.getMonth());
}catch(Exception e){}
        return new Report(tracker,transport,km,time_work,time_stop);

    }

    //метод загружает данные в список с Excell отчета
    public void loadExcell(String file_path) throws IOException{
        XSSFCell cell = null;
        Row row = null;
        FileInputStream inputStream = new FileInputStream(file_path);
        XSSFWorkbook workbook  = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int row_total = sheet.getLastRowNum();
        //ата отчета(движения транспорта)
        String data = sheet.getRow(2).getCell(0).toString().substring(10,20);
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy, MM, dd");
        try {
            date = sdf.parse(data.substring(6,10)+", "+data.substring(3,5)+", "+data.substring(0,2));

        } catch (ParseException e) {
            System.out.print("Can not get report date!");
            e.printStackTrace();
        }

        String pointer ;
        Report unitreport;

        for(int i= 4;i<row_total;i++){
            row = sheet.getRow(i);
            cell= (XSSFCell) row.getCell(0);

            //читает с файла треккер, название комбайна, и конвертирует в Report объект
            try{
                pointer = cell.toString();

                if(pointer.contains("Транспортное средство")==true){
                    unitreport = convertStringtoTransport(pointer,row.getCell(11).toString());
                    i=i+3;
                    for(;i<row_total;i++){
                        row =sheet.getRow(i) ;
                        if(i+1>row_total||(((XSSFCell) (sheet.getRow(i+1)).getCell(0)).toString()).contains("Транспортное средство")==true){
                            break;
                        }

                        TransportAction transportAction = new TransportAction(
                                String.valueOf(row.getCell(1)),
                                String.valueOf(row.getCell(2)),
                                String.valueOf(row.getCell(4)),
                                String.valueOf(row.getCell(5)),
                                String.valueOf(row.getCell(7)),
                                String.valueOf(row.getCell(8)),
                                String.valueOf(row.getCell(9)),
                                String.valueOf(row.getCell(12)),
                                String.valueOf(row.getCell(13)));



                        unitreport.addTransportAction(transportAction);

                    }
                    reports.add(unitreport);

                }
            }catch (Exception e){
                System.out.println("Ошибка чтения с файла треккера, комбайна, и и конвертирования!");
                throw new IOException("Ошибка чтения с файла треккера, комбайна, и и конвертирования!");
            }

        }
    }


    public  ArrayList<Report> getReports() {
        return reports;
    }
}
