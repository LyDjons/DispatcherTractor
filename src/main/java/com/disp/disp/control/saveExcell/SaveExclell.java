package com.disp.disp.control.saveExcell;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by disp.chimc on 31.10.14.
 */
public class SaveExclell {
    private  FileOutputStream fos;
    private  FileInputStream inputStream;
    private XSSFWorkbook workbook;
    private Sheet sheet;

    //установка размеров колонок
    private  void setSizeColumn(Sheet sheet){
        sheet.setColumnWidth(0,3700);
        sheet.setColumnWidth(1,6107);
        sheet.setColumnWidth(2,2925);
        sheet.setColumnWidth(3,6619);
        sheet.setColumnWidth(4,2500);
        sheet.setColumnWidth(5,5668);
        sheet.setColumnWidth(6,1700);
        for(int i = 7 ; i < 103;i++)
            sheet.setColumnWidth(i,420);
    }

    //зарисовать ячейку любым цветом
    private  void driwing_cell(int row, int cell,Color color){
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(new XSSFColor(color)); //цвет ячейки
            style.setFillPattern(CellStyle.SOLID_FOREGROUND); //?? установить цвет
            style.setBorderTop(CellStyle.BORDER_THIN);
             style.setBorderBottom(CellStyle.BORDER_THIN);
            sheet.getRow(row).getCell(cell).setCellStyle(style); //применить стиль
    }

    //конструктор класса path-путь к файлу  createlistname- имя листа
    public SaveExclell(String path,String createlistname) {
        try {
            inputStream = new FileInputStream(path);
            workbook  = new XSSFWorkbook(inputStream);
            fos = new FileOutputStream(path);
            sheet = workbook.createSheet(createlistname);
        } catch (FileNotFoundException e) {
            System.out.println("Can not find file!!!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error SaveExcel Constructor");
            e.printStackTrace();
        }

    }

    //создание шапки листа
    public void createHatList() throws IOException {
//установка размера колонок
        setSizeColumn(sheet);
        sheet.createRow(1).createCell(0).setCellValue("Робота тракторів");
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER); //выровняли по центру
        style.setFillForegroundColor(new XSSFColor(new Color(255,255,0))); //цвет ячейки
        style.setFillPattern(CellStyle.SOLID_FOREGROUND); //?? установить цвет
        sheet.getRow(1).getCell(0).setCellStyle(style); //применить стиль
//выделяем и объединяем Ячейки
        CellRangeAddress region = new CellRangeAddress(1, 1, 0, 1);//(firstRow,lastRow,firstCol,lastCol)
        sheet.addMergedRegion(region);
//Проворачивает дела с шапкой
        Row cap_row = workbook.getSheet(sheet.getSheetName()).createRow(2);
        int count =7;
        XSSFCellStyle style2 = workbook.createCellStyle();
        for(int i = 7; i < 103; i=i+4){
            if(count==25) count=1;
            cap_row.createCell(i).setCellValue(count++);
            region = new CellRangeAddress(2, 2, i, i+3);//(firstRow,lastRow,firstCol,lastCol)
            RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, region, sheet, workbook);
            RegionUtil.setBorderLeft(CellStyle.BORDER_MEDIUM, region, sheet, workbook);
            RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, region, sheet, workbook);
            RegionUtil.setBorderBottom(CellStyle.BORDER_MEDIUM, region, sheet, workbook);
            sheet.addMergedRegion(region);
            style2.setAlignment(CellStyle.ALIGN_CENTER); //выровняли по центру
            style2.setFillForegroundColor(new XSSFColor(new Color(191,191,191))); //цвет ячейки
            style2.setFillPattern(CellStyle.SOLID_FOREGROUND); //?? установить цвет
            sheet.getRow(2).getCell(i).setCellStyle(style2); //применить стиль
        }
        cap_row.createCell(0).setCellValue("Відділення");
        cap_row.createCell(1).setCellValue("Марка ТЗ");
        cap_row.createCell(2).setCellValue("Держ. №");
        cap_row.createCell(3).setCellValue("Агрегат");
        cap_row.createCell(4).setCellValue("Инв. ном.");
        cap_row.createCell(5).setCellValue("Вид робіт");
        cap_row.createCell(6).setCellValue("К-ть Га");

        for(int i=0;i<8;i++) sheet.getRow(2).getCell(i).setCellStyle(style2);
        for(int i=0; i<8;i++){
            CellStyle cellStyle = sheet.getRow(2).getCell(i).getCellStyle();
            cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
            cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
        }
    }

    //нарисовать отчет
    private void driwing_report(Color work,Color stop,ArrayList<TransportExcell> transportlist){
        try{
            int row1 =4;
            for(TransportExcell tr: transportlist){
                int start = 7;
                if(tr.getStart().getHours()>=7) {
                    start = tr.get_num_cell(tr.getStart());
                }
                int end = tr.get_num_cell(tr.getEnd());


                for(int i = start; i<end;i++){
                    driwing_cell(row1,i,new Color(0,176,80));
                }
                row1++;
            }
            row1=4;
            for(TransportExcell tr: transportlist){

                for(Pinter p:tr.getPintersList()){

                        if(p.getMin()>15){
                        for(int i =p.getStart();i<p.getEnd();i++){
                            driwing_cell(row1,i,p.getColor());
                        }
                        }
                }
                row1++;
            }

            //рисуем переезды
            row1=4;
            for(TransportExcell tr: transportlist){

                for(Pinter p:tr.getPintersList()){
                    if(p.getColor().equals(new Color(0,176,240))){

                        for(int i =p.getStart();i<p.getEnd();i++){
                            driwing_cell(row1,i,p.getColor());
                        }
                    }
                }
                row1++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //заполнение таблици
    public void create(ArrayList<TransportExcell> transportlist) throws IOException {
        int row = 4;
        //заполняем  инф. таблицу
        for(TransportExcell tr: transportlist){
            sheet.createRow(row).createCell(1).setCellValue(tr.getTransport_mark());
            sheet.getRow(row).createCell(0).setCellValue(tr.getDepartment());
            sheet.getRow(row).createCell(2).setCellValue(tr.getGos());
            sheet.getRow(row).createCell(3).setCellValue(tr.getAgregat());
            sheet.getRow(row).createCell(4).setCellValue(tr.getInv());
            sheet.getRow(row).createCell(5).setCellValue(tr.getType_of_work());
            row++;
        }

        //создаем сетку
        for(int r = 3;r<transportlist.size()+4;r++){
            for(int i = 0;i<103;i++){
            CellRangeAddress region = new CellRangeAddress(r, r, i, i);//(firstRow,lastRow,firstCol,lastCol)
                RegionUtil.setBorderTop(CellStyle.BORDER_THIN, region, sheet, workbook);
                RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, region, sheet, workbook);

                if (i<7){
                RegionUtil.setBorderRight(CellStyle.BORDER_THIN, region, sheet, workbook);
                    RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, region, sheet, workbook);
                }
            }

        }

        //исуем отчет
        driwing_report(new Color(0, 176, 80), new Color(255, 255, 0), transportlist);

    }

    public void update() throws IOException {
        workbook.write(fos);
    }


}
